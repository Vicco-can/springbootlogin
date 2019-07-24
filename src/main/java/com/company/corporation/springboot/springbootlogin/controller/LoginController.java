package com.company.corporation.springboot.springbootlogin.controller;

import com.company.corporation.springboot.springbootlogin.entity.User;
import com.company.corporation.springboot.springbootlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.*;

@Controller
//@RequestMapping("/api")
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String loginIndex() {
        return "login";
    }

    @RequestMapping("/success")
    public String loginSuccess() {
        return "success";
    }

    /**
     * 登录验证：
     * 首先验证用户名是否存在，若存在：
     * 验证该用户是否登录失败超过五次并且在5分钟锁定时间内，若否：
     * 验证用户名密码是否正确
     */
    @RequestMapping("/logincheck")
    @ResponseBody
    public Map login(HttpServletRequest request, String name, String password) {

        Map resultMap = new HashMap();
        System.out.println("用户名、密码： " + name + "  " + password);
        //验证用户名是否存在
        List<User> userListByName = userService.selectUserByName(name.trim());
        System.out.println("数据库中该用户数：" + userListByName.size());
        if (userListByName.size() == 0) {
            resultMap.put("error", "该用户不存在！");
            resultMap.put("code", 101);
            return resultMap;
        }
        //验证错误次数是否大于5次and时间在5min内
        //checkmissnum
        if (checkMissCount(userListByName)) {
            resultMap.put("error", "用户被锁定，请5分钟后再试！");
            resultMap.put("code", 101);
            return resultMap;
        }

        //验证用户名、密码
        List<User> userList = userService.selectUserByNamePwd(name, password);
        User userByName = userListByName.get(0);
        User updateUser = new User();
        updateUser.setUserName(name);
        if (userList.size() == 0) {//用户名密码错误
            resultMap.put("error", "用户名或密码错误！");
            resultMap.put("code", 101);
            //更新数据库, 登录错误次数加1，允许登录时间更新为当前时间+5分钟
            if (userByName.getMisscount() == null || userByName.getAllowtime() == null
                    || Instant.now().isAfter(userByName.getAllowtime().toInstant().plusSeconds(300)))//若锁定时间已超五分钟，登录错误次数清零
            {
                userByName.setMisscount(0);
            }
            int nMissCount = userByName.getMisscount() + 1;
            Date dtAllowTime = Date.from(Instant.now().plusSeconds(300));
            updateUser.setMisscount(nMissCount);
            updateUser.setAllowtime(dtAllowTime);
            System.out.println(dtAllowTime);
            userService.updateUser(updateUser);

        } else {//验证成功
            resultMap.put("code", 100);
            resultMap.put("companyid", userList.get(0).getCompanyId());
            List<String> listCompanyId = new ArrayList<>();
            for (User user : userList) {
                listCompanyId.add(user.getCompanyId());
            }
            resultMap.put("listcompanyid", listCompanyId);
            //更新数据库，登录错误次数清零
            updateUser.setMisscount(0);
            System.out.println(updateUser.getAllowtime());
            userService.updateUser(updateUser);

        }

        return resultMap;
    }

    /***
     * 验证用户是否需要锁定：错误登录次数五分钟内超5次
     * @param userList
     * @return true需要锁定
     */
    private boolean checkMissCount(List<User> userList) {
        if (userList.size() == 0) {
            return true;
        }
        Instant dateNow = Instant.now();
        User user = userList.get(0);
        Instant dateAllowTime = dateNow.minusSeconds(300);
        if (user.getAllowtime() != null) {
            dateAllowTime = user.getAllowtime().toInstant();
        }
        if (user.getMisscount() == null || dateNow.isAfter(dateAllowTime.plusSeconds(300))) {
            user.setMisscount(0);
        }

        if (user.getMisscount() >= 5 && dateNow.isBefore(dateAllowTime)) {
            return true;
        }
        return false;
    }
}
