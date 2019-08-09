package com.yaner.house.service.impl;

import com.yaner.house.bean.Admin;
import com.yaner.house.bean.User;
import com.yaner.house.mapper.LoginMapper;
import com.yaner.house.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-23  9:41
 **/
@Service
public class LoginServiceImpl implements LoginService{
    //自动注入LoginMapper
    @Autowired
    private LoginMapper loginMapper;
    //登陆功能模块
    @Override
    public String loginJudge(HttpSession session, String admin_name, String admin_password) {
        System.out.println("进入了----------------------LoginServiceImpl");
        //先对admin_name与admin_password 进行非空判断
        //进入数据库查询
        Admin admin = new Admin();
        admin = loginMapper.loginJudge(admin_name,admin_password);
        //System.out.println("admin:"+admin.toString());
        String loginInfo = "";
        if (admin!=null){
            //登陆验证成功,把信息存入session
            session.setAttribute("admin_id",admin.getAdmin_id());
            session.setAttribute("admin_name",admin.getAdmin_name());
            session.setAttribute("admin_email",admin.getAdmin_email());
            session.setAttribute("admin_limit",admin.getAdmin_limit());
            session.setAttribute("admin_state",admin.getAdmin_state());
            System.out.println("存入session的admin_id:"+session.getAttribute("admin_id"));
            if ("-1".equals(admin.getAdmin_state())){
                loginInfo = "login004";//您的账号已被禁用！
            }else{
                if ("1".equals(admin.getAdmin_limit())){
                    //loginInfo="redirect:http://localhost:9088/index.html";//登陆成功跳转index.html---普通管理员
                    loginInfo = "login001";
                }else{
                    //loginInfo="redirect:http://localhost:9088/adminIndex.html";//登陆成功跳转adminIndex.html---超级管理员
                    loginInfo = "login002";
                }
            }

        }else{
             loginInfo = "login003";//用户名或者密码错误
        }
        return loginInfo;
    }

    @Override
    public String getLoginInfo(HttpSession session) {
        return session.getAttribute("admin_name").toString();
    }

    @Override
    public String emptyLogin(HttpSession session) {
        session.removeAttribute("admin_id");
        session.removeAttribute("admin_name");
        session.removeAttribute("admin_email");
        session.removeAttribute("admin_limit");
        session.removeAttribute("admin_state");
        return "emptyLogin001";
    }
    //判断admin是否登陆
    @Override
    public String isLogin(HttpSession session) {
        if ((session.getAttribute("admin_id"))==null){
            return "isLoginInfo002";//代表未登录，需要跳转到登陆页面
        }else {
            return "isLoginInfo001";//代表登录过，可以进行操作
        }
    }

    @Override
    public String WXloginJudge(String user_name, String user_password) {
        //进入数据库查询
        User user = loginMapper.WXloginJudge(user_name,user_password);
        String loginInfo = "";
        if (user!=null) {
            if ("-1".equals(user.getUser_state())) {
                loginInfo = "login004";//您的账号已被禁用！
            } else {
                if ("1".equals(user.getUser_state())) {
                    //查询登录的user_id
                    String user_id = loginMapper.WXLoginUserId(user_name,user_password);
                    //返回user_id
                    loginInfo = user_id;//用户名ID
                }
            }
        }else{

            loginInfo = "login003";//用户名或者密码错误
        }
        return loginInfo;
    }


}
