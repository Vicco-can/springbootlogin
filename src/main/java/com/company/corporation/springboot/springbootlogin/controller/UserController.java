package com.company.corporation.springboot.springbootlogin.controller;

import com.company.corporation.springboot.springbootlogin.entity.User;
import com.company.corporation.springboot.springbootlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/finduser")
    public User findUserById(@RequestParam(value = "id", required = true)int id){
        User userResult = userService.selectUserByid(id);
        return userResult;
    }
}
