package com.company.corporation.springboot.springbootlogin.dao;

import com.company.corporation.springboot.springbootlogin.entity.User;

import java.util.Date;
import java.util.List;

public interface UserDao {

    //用于测试
    User selectUserByid(int id);

    List<User> selectUserByName(String name);

    List<User> selectUserByNamePwd(String name, String password);

    int updateUser(User user);

    int updateUserMissLogin(int misscount, Date allowtime, String name);
}
