package com.springboot.Demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.User;


@RestController
@EnableAutoConfiguration
@RequestMapping("/hello")
public class Controller {
    @RequestMapping("getuser")
    public User getUser() {
        User user = new User();
        user.setUserName("test");
        return user;
    }
    
    @RequestMapping("")
    public String hello() {
        return "hello wrold!";
    }
}
