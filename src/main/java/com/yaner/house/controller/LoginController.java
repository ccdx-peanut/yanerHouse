package com.yaner.house.controller;

import com.yaner.house.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author:sfq
 * @create 2019-04-23  9:39
 **/
@RestController
public class LoginController {
    //自动注入LoginService
    @Autowired
    private LoginService loginService;
    //登陆功能
    @RequestMapping("/loginJudge")
    public void loginJudge(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println("进入了登陆判断的Controller");
        try {
            request.setCharacterEncoding("utf-8");
            String sendInfo = request.getParameter("sendInfo");

            String loginIngo[] = sendInfo.split("-");
            System.out.println("loginIngo[0]:"+loginIngo[0]+"---"+"loginIngo[1]:"+loginIngo[1]);
            String returnInfo = loginService.loginJudge(session,loginIngo[0],loginIngo[1]);
            System.out.println("返回给AJAX的信息："+returnInfo);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(returnInfo);
            response.setHeader("Content-Type","text/html");
            out.flush();
            out.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //测试功能
    @RequestMapping("/loginTest")
    public void loginTest(){
        System.out.println("进入了测试的Controller");
    }

    //登陆过后页面显示登录名
    @RequestMapping("/getLoginInfo")
    public String getLoginInfo(HttpSession session){
        System.out.println("进入了getLoginInfo的Controller");
        return loginService.getLoginInfo(session);
    }

    //登陆退出功能
    @RequestMapping("/emptyLogin")
    public String emptyLogin(HttpSession session){
        System.out.println("进入了emptyLogin的Controller");
        return loginService.emptyLogin(session);
    }
    //判断admin是否登陆
    @RequestMapping("/isLogin")
    public String isLogin(HttpSession session){
        System.out.println("进入了isLogin的Controller");
        return loginService.isLogin(session);
    }


}
