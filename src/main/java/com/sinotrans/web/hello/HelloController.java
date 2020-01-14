package com.sinotrans.web.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinotrans.security.SecurityUtil;

import lombok.var;

@RestController
@RequestMapping
public class HelloController {
    
    
    @RequestMapping("/hello")
    public String hello() {
        var name = "";
        if (SecurityUtil.getCurrentUser().isPresent()) {
            name = SecurityUtil.getCurrentUser().get().getUsername();
        }
        return "hello, " + name;
    }
}
