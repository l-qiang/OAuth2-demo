package com.sinotrans.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import lombok.Setter;
import lombok.var;

public class QyWechatAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter{
    
    public static final String QY_WECHAT_CODE = "code";
    public static final String QY_WECHAT_STATE = "state";
    
    private String codeParameter = QY_WECHAT_CODE;
    private String stateParameter = QY_WECHAT_STATE;
    
    private @Setter WechatUserProcessService wechatUserProcessService;
    
    public QyWechatAuthenticationProcessingFilter() {
        super("/qywechat/back");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        
        var code = obtainCode(request);
        var state = obtainState(request);
        
        if (code == null) {
            throw new AuthenticationServiceException("企业微信未授权");
        }
        if (!wechatUserProcessService.verifyState((String)state)) {
            throw new AuthenticationServiceException("状态不一致");
        }
        
        var userId = wechatUserProcessService.getUserId((String)code);
        
        var authRequest = new QyWeChatAuthenticationToken(userId);
        
        setDetails(request, authRequest);
        
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private Object obtainCode(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }
    
    private Object obtainState(HttpServletRequest request) {
        return request.getParameter(stateParameter);
    }
    
    protected void setDetails(HttpServletRequest request,
            QyWeChatAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
