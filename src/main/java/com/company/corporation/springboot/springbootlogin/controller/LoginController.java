package com.company.corporation.springboot.springbootlogin.controller;

import com.company.corporation.springboot.springbootlogin.entity.User;
import com.company.corporation.springboot.springbootlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/api")
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String loginIndex() {
        return "login";
    }

    /**
     * 登录验证：
     * 首先验证用户名是否存在，若存在：
     * 验证该用户是否登录失败超过五次并且在5分钟锁定时间内，若否：
     * 验证用户名密码是否正确
     */
    @RequestMapping("/logincheck")
    @ResponseBody
    public Map login(HttpServletRequest request, String userName,String password) {

        Map resultMap = new HashMap();
        //验证用户名是否存在
        List<User> userListByName = userService.selectUserByName(userName);
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
        List<User> userList = userService.selectUserByNamePwd(userName, password);
        User updateUser = new User();
        if (userList.size() == 0) {//用户名密码错误
            resultMap.put("error", "用户名或密码错误！");
            resultMap.put("code", 101);
            //更新数据库, 登录错误次数加1，允许登录时间更新为当前时间+5分钟
            int nMissCount = userListByName.get(0).getMisscount() + 1;
            Date dtAllowTime = Date.from(Instant.now().plusSeconds(300));

            updateUser.setMisscount(nMissCount);
            updateUser.setAllowtime(dtAllowTime);
            userService.updateUser(updateUser);
//            userService.updateUserMissLogin(nMissCount, dtNow, userName);

        } else {//验证成功
//            resultMap.put("error", "用户被锁定，请5分钟后再试！");
            resultMap.put("code", 100);
            //更新数据库，登录错误次数清零
            updateUser.setMisscount(0);
            userService.updateUser(updateUser);

        }

        return resultMap;
    }

    private boolean checkMissCount(List<User> userList) {
        if (userList.size() == 0) {
            return true;
        }

        User user = userList.get(0);
        Instant dateAllowTime = user.getAllowtime().toInstant();
        Instant dateNow = Instant.now();

        if (user.getMisscount() >= 5 && dateNow.isBefore(dateAllowTime)) {
            return true;
        }
        return false;
    }
}
