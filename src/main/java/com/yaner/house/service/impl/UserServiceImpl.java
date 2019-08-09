package com.yaner.house.service.impl;

import com.yaner.house.bean.User;
import com.yaner.house.mapper.MoneyMapper;
import com.yaner.house.mapper.UserMapper;
import com.yaner.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:51
 **/
@Service
public class UserServiceImpl implements UserService{
    //自动注入UserMapper
    @Autowired
    private UserMapper userMapper;
    //自动注入moneyMapper
    @Autowired
    private MoneyMapper moneyMapper;
    //添加用户
    @Override
    public String addUser(User user) {
        String returnInfo = "";
        //进入数据库查询所有用户登陆的用户名
        List<String> userNameLists = userMapper.getAllUserName();
        //判断用户名是否重复
        if (userNameLists.contains(user.getUser_name())){
            //用户名重复
            return returnInfo = "addUser002";
        }else {
            //因为传递过来的user_six 是 1或者2 所以要进行数据转换为男或女
            userSixTool(user);
            //性别数据转换结束
            System.out.println(user.toString());
            int i = userMapper.addUser(user);
            if (i>0){
                //得到最后添加的用户的ID
                String lastUserId = userMapper.getLastAddUser();
                //在数据库中的money的表中添加一天余额记录
                moneyMapper.addMoneyRecord(lastUserId);
                return returnInfo = "addUser001";//添加用户成功
            }else{
                return returnInfo = "addUser003";//添加用户失败
            }
        }


    }
    //修改用户
    @Override
    public void updateUser(User user) {
        //不修改性别
       // userSixTool(user);
        //性别数据转换结束
        userMapper.updateUser(user);
    }
    //查询所有状态正常的用户信息：user_state = 1
    //要把状态1的改为正常
    @Override
    public List<User> getUsers() {
        List<User> usersList = userMapper.getUsers();

        for (int i=0 ;i<usersList.size() ;i++){
            //从数据库中查询出来的状态的值为1，转成"正常"
            if("1".equals(usersList.get(i).getUser_state())){
                usersList.get(i).setUser_state("正常");
            }
        }
        //System.out.println(usersList);
        return usersList;
    }
    //删除用户： user_state=-1，假删除
    @Override
    public void deleteUser(User user) {
        //从数据库中修改对应ID的状态的值为-1
        userMapper.deleteUser(user);
    }
    //性别转换工具
    @Override
    public void userSixTool(User user) {
        //因为传递过来的user_six 是 1或者2 所以要进行数据转换为男或女
        if("1".equals(user.getUser_six())){
            user.setUser_six("男");
        }else if ("2".equals(user.getUser_six())){
            user.setUser_six("女");
        }
    }
    //查询用户信息By user_id
    @Override
    public List<User> getUserByid(String user_id) {
        return userMapper.getUserByid(user_id);
    }

    @Override
    public String WXupdateUserInfo(String user_email, String user_phone, String user_IdCard, String user_realName, String user_id) {
        int aa = userMapper.WXupdateUserInfo(user_email,user_phone,user_IdCard,user_realName,user_id);
        if (aa > 0){
            return "WXupdateUserInfo001";//修改用户信息成功
        }else{
            return "WXupdateUserInfo002";//修改用户信息失败
        }

    }

    @Override
    public String WxNextController(String user_name) {
        String returnInfo = "";
        //进入数据库查询所有用户登陆的用户名
        List<String> userNameLists = userMapper.getAllUserName();
        //判断用户名是否存在
        if (userNameLists.contains(user_name)){
            //用户名存在
            return returnInfo = "zhanshi002";
        }else{
            //未找到该用户信息
            return returnInfo = "nextInfo001";
        }
    }
  /*  //查询所有用户登陆的用户名
    @Override
    public List<String> getAllUserName() {
        //进入数据库查询
        return userMapper.getAllUserName();
         }*/

}
