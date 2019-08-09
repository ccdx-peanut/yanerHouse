package com.yaner.house.service;

import com.yaner.house.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:49
 **/
@Service
public interface UserService {
    //添加用户
    String addUser(User user);
    //修改用户根据ID
    void updateUser(User user);
    //查询所有状态正常的用户信息：user_state = 1
    List<User> getUsers();
    //删除用户： user_state=-1，假删除
    void deleteUser(User user);
    //转换用户性别信息的工具
    void userSixTool(User user);
    /*//查询所有的用户登录名
    List<String> getAllUserName();*/
    //查询用户信息By user_id
    List<User> getUserByid(String user_id);
    //微信小程序修改用户信息By user_id
    String WXupdateUserInfo(String user_email,String user_phone,String user_IdCard,String user_realName,String user_id);
    //微信查询用户名，且发送邮件
    String WxNextController(String user_name);
}
