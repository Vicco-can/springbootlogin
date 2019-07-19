package com.company.corporation.springboot.springbootlogin.service;

import com.company.corporation.springboot.springbootlogin.entity.User;

public interface IUserService {
    User selectUserByid(int id);
}
