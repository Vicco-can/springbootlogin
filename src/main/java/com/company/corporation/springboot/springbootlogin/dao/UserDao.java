package com.company.corporation.springboot.springbootlogin.dao;

import com.company.corporation.springboot.springbootlogin.entity.User;

public interface UserDao {

    User selectUserByid(int id);
}
