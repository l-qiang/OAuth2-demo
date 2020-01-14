package com.sinotrans;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BCryptPasswordEncoderTest {
    
    @Test
    public void getEncodePassword() {
        log.info(new BCryptPasswordEncoder().encode("sinopro"));
    }
}
