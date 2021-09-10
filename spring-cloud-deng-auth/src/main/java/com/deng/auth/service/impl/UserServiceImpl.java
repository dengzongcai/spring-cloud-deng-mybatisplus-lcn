package com.deng.auth.service.impl;

import com.deng.auth.domain.SecurityUser;
import com.deng.commons.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: UserServiceImpl 
 * @Description: 用户管理业务类
 * @author ZongCai
 * @date 2021/7/13
 */

@Service
public class UserServiceImpl implements UserDetailsService {
    @Resource
    private HttpServletRequest request;

    /**
     * 生成token
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        String password = request.getParameter("password");
        UserDTO userDto = new UserDTO();
        List<String> roles = new ArrayList<String>();
        roles.add("/deng-auth/hello");
        userDto.setClientId(clientId);
        userDto.setStatus(1);
        // 权限集合
        userDto.setRoles(roles);
        userDto.setPassword(new BCryptPasswordEncoder().encode(password));
        userDto.setUsername(username);
        SecurityUser securityUser = new SecurityUser(userDto);
        return securityUser;
    }

}
