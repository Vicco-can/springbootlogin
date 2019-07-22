package com.company.corporation.springboot.springbootlogin.service.impl;

import com.company.corporation.springboot.springbootlogin.dao.UserDao;
import com.company.corporation.springboot.springbootlogin.entity.User;
import com.company.corporation.springboot.springbootlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserByid(int id){
        return userDao.selectUserByid(id);
    }

    @Override
    public List<User> selectUserByName(String name){
        return userDao.selectUserByName(name);
    }

    @Override
    public List<User> selectUserByNamePwd(String name, String password){
        return userDao.selectUserByNamePwd(name, password);
    }

    @Override
    public int updateUserMissLogin(int misscount, Date allowtime, String name){
        return userDao.updateUserMissLogin(misscount, allowtime, name);
    }

    @Override
    public int updateUser(User user){
        return userDao.updateUser(user);
    }
}
