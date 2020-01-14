package com.sinotrans.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import com.sinotrans.security.MyLoginFailureHandler;
import com.sinotrans.security.MyLoginInSuccessHandler;
import com.sinotrans.security.QyWechatAuthenticationSecurityConfig;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    
    @Autowired
    private DefaultTokenServices defaultTokenServices;
    @Autowired
    private QyWechatAuthenticationSecurityConfig qyWechatAuthenticationSecurityConfig;
    @Autowired
    private MyLoginInSuccessHandler myLoginInSuccessHandler;
    @Autowired
    private MyLoginFailureHandler myLoginFailureHandler;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.tokenServices(defaultTokenServices);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(myLoginInSuccessHandler)
        .failureHandler(myLoginFailureHandler)
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .apply(qyWechatAuthenticationSecurityConfig);
    }
}
