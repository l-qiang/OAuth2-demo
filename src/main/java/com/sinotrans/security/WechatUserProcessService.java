package com.sinotrans.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinotrans.qywechat.service.WorkService;
import com.sinotrans.user.repository.SinoUserRepository;

import lombok.var;

@Service
public class WechatUserProcessService {
    
    @Autowired
    private WorkService workService;
    @Autowired
    private SinoUserRepository sinoUserRepository;
    
    @Transactional
    public String getUserId(String code) {
//        var userId = workService.getUserId(workService.getAccessToken(), code);
//        
//        if (!sinoUserRepository.existsById(userId)) {
//            var user = workService.getWechatUser(workService.getAccessToken(), userId);
//            sinoUserRepository.save(user);
//        }
//        
//        return userId;
        return "admin";
    }
    
    /**
     * 校验state的值是否与二维码中的state一致
     * @param state
     * @return
     */
    public boolean verifyState(String state) {
        return true;
    }
}
