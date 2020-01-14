package com.sinotrans.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.Setter;
import lombok.var;

public class QyWechatAuthenticationProvider implements AuthenticationProvider {
    
    private @Setter SinoUserDetailsService sinoUserDetailsService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = sinoUserDetailsService.loadUserByUsername((String)authentication.getPrincipal());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        
        var authenticationResult = new QyWeChatAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authentication.getDetails());
        
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return QyWeChatAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
