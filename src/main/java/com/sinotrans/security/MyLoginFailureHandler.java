package com.sinotrans.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.var;

@Component("myLoginFailureHandler")
public class MyLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        var message = "";
        response.setContentType("application/json;charset=UTF-8");
        if(exception instanceof BadCredentialsException){
            message = "用户名或密码错误";
            var res = new HashMap<String, Object>();
            res.put("code", "-1");
            res.put("msg", message);
            response.getWriter().write(objectMapper.writeValueAsString(res));
        }
    }
}
