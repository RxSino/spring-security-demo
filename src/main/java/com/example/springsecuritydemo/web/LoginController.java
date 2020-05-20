package com.example.springsecuritydemo.web;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        System.out.println("username= " + username);
        return "注册成功";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        System.out.println("username= " + username);
        return "登陆成功";
    }

}

