package com.sinotrans.qywechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.sinotrans.qywechat.api.WorkApi;
import com.sinotrans.qywechat.properties.WechatProperties;
import com.sinotrans.qywechat.response.ErrorResponseException;
import com.sinotrans.user.entity.SinoUser;

import lombok.Lombok;
import lombok.var;

@Service
public class WorkService {
    
    @Autowired private EhCacheCacheManager ehCacheCacheManager;
    @Autowired private WechatProperties wechatProperties;
    @Autowired private WorkApi workApi;
    
    @Cacheable(cacheNames="QyWechatAccessToken", key="'QyWechatAccessTokenKey'")
    public String getAccessToken() {
        var reponse = workApi.getToken(wechatProperties.getCorpId(), wechatProperties.getSecret());
        if (reponse.getErrcode() != 0) {
            Lombok.sneakyThrow(new ErrorResponseException("获取企业微信AccessToken失败"));
        }
        return reponse.getAccessToken();
    }
    
    public String getAccessTokenAndRefreshCache() {
        var token = getAccessToken();
        ehCacheCacheManager.getCache("QyWechatAccessToken").put("QyWechatAccessTokenKey", token);
        return token;
    }
    
    public String getUserId(String token, String code) {
        var reponse = workApi.getUserInfo(token, code);
        if (reponse.getErrcode() != 0) {
            reponse = workApi.getUserInfo(getAccessTokenAndRefreshCache(), code);
        }
        if (reponse.getErrcode() != 0) {
            Lombok.sneakyThrow(new ErrorResponseException("获取企业微信userId失败"));
        }
        return reponse.getUserId();
    }
    
    public SinoUser getWechatUser(String token, String userId) {
        var reponse = workApi.getUser(token, userId);
        if (reponse.getErrcode() != 0) {
            reponse = workApi.getUser(getAccessTokenAndRefreshCache(), userId);
        }
        if (reponse.getErrcode() != 0) {
            Lombok.sneakyThrow(new ErrorResponseException("获取企业微信用户信息失败"));
        }
        return new SinoUser(reponse.getUserid(), "", reponse.getEmail(), reponse.getMobile(), null);
    }
    
    public WechatProperties getWechatProperties() {
        return wechatProperties;
    }
}
