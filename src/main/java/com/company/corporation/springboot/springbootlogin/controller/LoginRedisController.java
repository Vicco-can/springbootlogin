package com.company.corporation.springboot.springbootlogin.controller;

import com.company.corporation.springboot.springbootlogin.entity.User;
import com.company.corporation.springboot.springbootlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginRedisController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("index")
    public String index() {
        return "login";
    }

    /**
     * 用户登录验证：
     * 首先验证用户名是否存在，若存在：
     * 验证该用户是否被锁定：从缓存中取出该用户登录错误次数，若没有锁定：
     * 验证该用户名密码是否正确，若正确，缓存错误登录次数置零，若错误，缓存登录次数+1，过期时间更新
     * @param request
     * @param name
     * @param password
     * @return
     */

    @RequestMapping("checklogin")
    @ResponseBody
    public Map login(HttpServletRequest request, String name, String password) {
        String key = "user" + "_" + name;
        ValueOperations operations = redisTemplate.opsForValue();
        Map resultMap = new HashMap();
        //验证用户名是否存在
        List<User> userListByName = userService.selectUserByName(name.trim());
        if (userListByName.size() == 0) {
            resultMap.put("error", "该用户不存在！");
            resultMap.put("code", 101);
            return resultMap;
        }
        Integer errorCount = (Integer) operations.get(key);
        if (errorCount == null) {
            errorCount = 0;
            operations.set(key, errorCount);
        }
        System.out.println("origin key: " + key + "  count: " + errorCount);
        //被锁定，无法登录，验证错误次数是否大于5次
        if (errorCount >= 5) {
            resultMap.put("error", "用户被锁定，请5分钟后再试！");
            resultMap.put("code", 101);
            return resultMap;
        }

        //验证用户名、密码
        List<User> userList = userService.selectUserByNamePwd(name, password);
        if (userList.size() == 0) {//用户名密码错误
            resultMap.put("error", "用户名或密码错误！");
            resultMap.put("code", 101);
            //更新缓存, 登录错误次数加1
            errorCount += 1;
//            operations.set(key, errorCount);
            operations.set(key, errorCount, 300, TimeUnit.SECONDS);

        } else {//验证成功
            resultMap.put("code", 100);
//            resultMap.put("companyid", userList.get(0).getCompanyId());
            List<String> listCompanyId = new ArrayList<>();
            for (User user : userList) {
                listCompanyId.add(user.getCompanyId());
            }
            resultMap.put("listcompanyid", listCompanyId);
            //更新缓存，登录错误次数清零
            operations.set(key, 0);
        }
        System.out.println("key: " + key + "  count: " + errorCount);
        return resultMap;
    }

}
