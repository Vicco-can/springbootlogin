package com.company.corporation.springboot.springbootlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class LoginController {

    @RequestMapping("/login")
    public String loginIndex(){
        return "login";
    }

}
