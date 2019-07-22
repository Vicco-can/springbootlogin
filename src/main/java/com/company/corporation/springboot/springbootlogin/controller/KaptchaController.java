package com.company.corporation.springboot.springbootlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class KaptchaController {

//    private static  final transient Logger log = Logger.getLogger(KaptchaController.class);

    /**
     * @Title: loginCheck
     * @param request
     * @param kaptchaReceived
     * @return String
     * @Description:  验证码是否正确
     */

    @RequestMapping(value = "kaptcha", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(HttpServletRequest request,
//	            @RequestParam(value = "username", required = true) String username,
//	            @RequestParam(value = "password", required = true) String password,
                             @RequestParam(value = "kaptcha", required = true) String kaptchaReceived){
        //验证码的值
        String kaptchaExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        //校验验证码是否正确
        if (kaptchaReceived == null || !kaptchaReceived.equals(kaptchaExpected)) {
//            log.info("验证码错了");
            return "kaptcha_error";//返回验证码错误

        }

        //校验用户名密码

        // ……

        // ……

//        log.info("验证码对了");
        return "success"; //校验通过返回成功

    }


}
