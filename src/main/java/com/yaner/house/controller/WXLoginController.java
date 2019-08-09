package com.yaner.house.controller;

import com.yaner.house.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:sfq
 * @create 2019-05-08  12:18
 **/
@RestController
public class WXLoginController {
    //自动注入LoginService
    @Autowired
    private LoginService loginService;
    //微信小程序登陆判断
    @RequestMapping("/myWXLogin")
    public String myLogin(HttpServletRequest request){
        System.out.println("进入了myLogin的Controller");
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        //System.out.println(user_name);
        String aaa = loginService.WXloginJudge(user_name,user_password);
        return aaa;
    }
}
