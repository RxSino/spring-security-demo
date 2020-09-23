package com.example.springsecuritydemo.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        System.out.println("username= " + username);
        return "注册成功";
    }

}

