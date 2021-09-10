package com.deng.gateway.authorization;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.deng.gateway.common.AuthConstant;
import com.deng.gateway.common.UserDto;
import com.deng.gateway.config.IgnoreUrlsConfig;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @ClassName: AuthorizationManager 
 * @Description: 鉴权管理器，用于判断是否有资源的访问权限
 * @Auther ZongCai
 * @Date 2021/7/8
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
    	log.info("进入AuthorizationManager，角色权限管理");
    	ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        //对应跨域的预检请求直接放行
        if(request.getMethod()== HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }
        //不同用户体系登录不允许互相访问
        try {
            String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
            if(StrUtil.isEmpty(token)){
                return Mono.just(new AuthorizationDecision(false));
            }
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();// {"user_name":"username","scope":["all"],"id":10000,"exp":1612235567,"authorities":["/goldendays-auth/hello","/goldendays-auth/hello-1"],"jti":"1e3b0b0a-a621-45c2-a596-40256c1b8ea5","client_id":"admin-app"}
            UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
            if (AuthConstant.ADMIN_CLIENT_ID.equals(userDto.getClientId())) {
                return Mono.just(new AuthorizationDecision(true));
            }
            if (AuthConstant.PORTAL_CLIENT_ID.equals(userDto.getClientId())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return Mono.just(new AuthorizationDecision(false));
        }
        //非管理端路径直接放行
        /*if (!pathMatcher.match(AuthConstant.ADMIN_URL_PATTERN, uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }*/
        //管理端路径需校验权限
        Map<Object, Object> resourceRolesMap = new HashMap<Object, Object>();//这个地方做角色菜单权限使用 stringRedisTemplate.opsForHash().entries(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
        List<String> authorities = new ArrayList<>();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, uri.getPath())) {
                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
            }
        }
        authorities.add("/deng-auth/hello");// 用于判断是否有资源的访问权限
        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(true));
        return authorizationDecisionMono;
    }

}
