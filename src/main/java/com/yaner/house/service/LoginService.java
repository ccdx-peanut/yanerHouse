package com.yaner.house.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author:sfq
 * @create 2019-04-23  9:40
 **/
@Service
public interface LoginService {
    //登陆功能
    String loginJudge(HttpSession session, String admin_name, String admin_password);

    //登陆过后页面显示登录名
    public String getLoginInfo(HttpSession httpSession);

    //登陆退出功能
    public String emptyLogin(HttpSession session);

    //查询是否登陆
    public String isLogin(HttpSession session);

    //小程序登陆功能
    String WXloginJudge(String admin_name, String admin_password);

}
