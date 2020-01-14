package com.sinotrans.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.var;

@Configuration
public class QyWechatAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

    @Autowired
    private SinoUserDetailsService sinoUserDetailsService;
    @Autowired
    private WechatUserProcessService wechatUserProcessService;
    @Autowired
    private MyLoginInSuccessHandler myLoginInSuccessHandler;
    @Autowired
    private MyLoginFailureHandler myLoginFailureHandler;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        var filter = new QyWechatAuthenticationProcessingFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setWechatUserProcessService(wechatUserProcessService);
        filter.setAuthenticationSuccessHandler(myLoginInSuccessHandler);
        filter.setAuthenticationFailureHandler(myLoginFailureHandler);
        
        var provider = new QyWechatAuthenticationProvider();
        provider.setSinoUserDetailsService(sinoUserDetailsService);
        
        http.authenticationProvider(provider).addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
