package com.company.corporation.springboot.springbootlogin.service.impl;

import com.company.corporation.springboot.springbootlogin.dao.UserDao;
import com.company.corporation.springboot.springbootlogin.entity.User;
import com.company.corporation.springboot.springbootlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserByid(int id){
        return userDao.selectUserByid(id);
    }
}
