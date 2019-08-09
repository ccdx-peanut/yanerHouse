package com.yaner.house.controller;

import com.yaner.house.bean.User;
import com.yaner.house.service.MailService;
import com.yaner.house.service.MoneyService;
import com.yaner.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:45
 **/
@RestController
public class UserController {
    //自动注入UserService
    @Autowired
    private UserService userService;
    //自动注入MoneyService
    @Autowired
    private MoneyService moneyService;
    //自动注入MailService
    @Autowired
    private MailService mailService;
    //查询所有状态正常的用户信息：user_state = 1
    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        //System.out.println("用户信息s:"+userService.getUsers());
        return userService.getUsers();
    }
    //删除用户： user_state=-1，假删除
    @RequestMapping("/deleteUser")
    public void deleteUser(User user ){
        System.out.println("进入了禁用用户deleteUser");
        userService.deleteUser(user);
    }
    //添加用户
    @RequestMapping("/addUser")
    public String addUser(User user){
        System.out.println("进入了添加用户addUser");
        System.out.println(user.toString());
        return userService.addUser(user);
    }
    //修改用户
    @RequestMapping("/updateUser")
    public void updateUser(User user){
        System.out.println("进入了修改用户updateUser");
        userService.updateUser(user);
    }

    //修改用户
    @RequestMapping("/myTest")
    public String myTest(){
        System.out.println("进入了修改用户myTest");
        String aa = "00001";
        return  aa;
    }
    //修改用户
    @RequestMapping("/getUserById")
    public List<User> getUserById(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        return userService.getUserByid(user_id);
    }
    //得到用户余额
    @RequestMapping("/getMyBalance")
    public String getMyBalance(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        return moneyService.getBalanceByUserId(user_id);
    }
    //用户余额充值
    @RequestMapping("/myChongzhi4000")
    public String myChongzhi(HttpServletRequest request){
        System.out.println("进入了myChongzhi");
        String user_id = request.getParameter("user_id");
        String balance = request.getParameter("balance");
        return moneyService.myChongzhi(user_id,balance);
    }
    //微信修改用户信息
    @RequestMapping("/WXupdateUserInfo")
    public String WXupdateUserInfo(HttpServletRequest request){
        System.out.println("WXupdateUserInfo");
        String user_email = request.getParameter("user_email");
        String user_phone = request.getParameter("user_phone");
        String user_IdCard = request.getParameter("user_IdCard");
        String user_realName = request.getParameter("user_realName");
        String user_id = request.getParameter("user_id");
        return userService.WXupdateUserInfo(user_email,user_phone,user_IdCard,user_realName,user_id);

    }
    //微信添加用户
    @RequestMapping("/WXZhuCeUser")
    public String WXZhuCeUser(HttpServletRequest request){
        System.out.println("进入了添加用户WXAddUser");
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        String user_six = request.getParameter("user_six");
        String user_email = request.getParameter("user_email");
        String user_phone = request.getParameter("user_phone");
        String user_IdCard = request.getParameter("user_IdCard");
        String user_realName = request.getParameter("user_realName");
        User user = new User();
        user.setUser_name(user_name);
        user.setUser_password(user_password);
        user.setUser_six(user_six);
        user.setUser_email(user_email);
        user.setUser_phone(user_phone);
        user.setUser_IdCard(user_IdCard);
        user.setUser_realName(user_realName);

        System.out.println(user.toString());
        return userService.addUser(user);
    }
    //微信查询用户名，且发送邮件
    @RequestMapping("/WxNextController")
    public String WxNextController(HttpServletRequest request){

        System.out.println("进入了WxNextController");
        String user_name = request.getParameter("user_name");
        System.out.println("user_name:"+user_name);
        String aa = userService.WxNextController(user_name);
        if (!("extInfo001".equals(aa))){
            return mailService.sendYzToUser(user_name);
        }else{
            return "extInfo001";
        }
    }
}
