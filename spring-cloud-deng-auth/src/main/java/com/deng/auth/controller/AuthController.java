package com.deng.auth.controller;

import com.deng.commons.config.AuthConstant;
import com.deng.commons.dto.Oauth2TokenDTO;
import com.deng.commons.entitys.MbUser;
import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.enums.ResultEnum;
import com.deng.commons.utils.JedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: AuthController 
 * @Description: 自定义Oauth2获取令牌接口
 * @author ZongCai
 * @date 2021/7/13
 */
@RestController
@Api(tags = "认证中心登录认证")
@RequestMapping("/oauth")
@Slf4j
public class AuthController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private TokenEndpoint tokenEndpoint;

    @ApiOperation("Oauth2获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码"),
            @ApiImplicitParam(name = "accounttype", value = "账户类型 1.后台登录 2 app登录,4 VipApp登录",required = true,dataType = "String"),
            @ApiImplicitParam(name = "appType", value = "登陆类型 1：手机号密码登陆 2：验证码登陆",required = true, dataType = "String")

    })
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResultVo<Oauth2TokenDTO> postAccessToken(@ApiIgnore Principal principal, @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        String clientId = request.getParameter("client_id");
        String username = request.getParameter("username");
        String accounttype = request.getParameter("accounttype");
        //校验账号 密码
        //组装返回的信息
        Map<String, Object> usermap = new HashMap<>();
        MbUser mbUser = new MbUser();
        mbUser.setUserid(1L);
        usermap.put("mbuser", mbUser);
        JedisUtils.setStr(clientId+username,mbUser.getUserid().toString());
        ResultVo login = ResultVOUtils.success(usermap);
        if (login == null || login.getCode() != ResultEnum.SUCCESS.getCode()){
            return login;
        }
        log.info("进入AuthController获取token");
    	OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDTO oauth2TokenDto = Oauth2TokenDTO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();
        if(Integer.parseInt(accounttype) ==1){
            String userid= JedisUtils.getStr(clientId+username);
            oauth2TokenDto.setUserid(Long.valueOf(userid));
        }
        JedisUtils.del(clientId+username);
        return ResultVOUtils.success(oauth2TokenDto);
    }

}
