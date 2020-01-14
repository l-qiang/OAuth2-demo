package com.sinotrans.oauth2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.sinotrans.security.SinoUserDetailsService;

import lombok.var;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private SinoUserDetailsService sinoUserDetailsService;
    @Autowired
    private DefaultTokenServices defaultTokenServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints
            .tokenServices(defaultTokenServices)
            .userDetailsService(sinoUserDetailsService)
            .authenticationManager(authenticationManager); //password模式必须注入
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients.inMemory().withClient("sinopro").secret("{bcrypt}$2a$10$17M8HIps3Rm2KXeSQsoKUuIF191DpObkRJMWv24libpBBYyUfhYNu").authorizedGrantTypes("password").scopes("all");
    }
    
    @Bean
    public DefaultTokenServices defaultTokenServices(DataSource dataSource) {
        var tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(new JdbcTokenStore(dataSource));
        tokenService.setSupportRefreshToken(true); //支持refreshToken
        return tokenService;
    }
}
