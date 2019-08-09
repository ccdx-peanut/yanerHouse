package com.yaner.house.mapper;

import com.yaner.house.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:47
 **/
@Mapper
public interface UserMapper {
    //添加用户
    @Insert("insert into user (user_name,user_password,user_email,user_six,user_phone,user_IdCard,user_realName)" +
            "values (#{user_name},#{user_password},#{user_email},#{user_six},#{user_phone},#{user_IdCard},#{user_realName})")
    int addUser(User user);
    //修改用户根据ID
    @Update("update user set user_name=#{user_name},user_password=#{user_password},user_email=#{user_email}," +
            "user_phone=#{user_phone},user_IdCard=#{user_IdCard},user_realName=#{user_realName} where user_id = #{user_id}" )
    void updateUser(User user);

    //查询所有状态正常的用户信息：user_state = 1
    @Select("select * from user where user_state = 1")
    List<User> getUsers();

    //删除用户： user_state=-1，假删除
    @Update("update user set user_state=-1 where user_id =#{user_id}")
    void deleteUser(User user);

    //查询所有用户登陆的用户名
    @Select("select user_name from user")
    List<String> getAllUserName();

    //查询最近添加的用户的ID
    @Select("SELECT user_id FROM USER ORDER BY user_id DESC LIMIT 0 ,1")
    String getLastAddUser();

    //查询用户信息By user_id
    @Select("select * from user where user_id = #{user_id}")
    List<User> getUserByid(String user_id);

    //微信小程序修改用户信息By user_id
    @Update("update user set user_email=#{user_email}," +
            "  user_phone=#{user_phone},user_IdCard=#{user_IdCard},user_realName=#{user_realName} where user_id = #{user_id}")
    int WXupdateUserInfo(@Param("user_email") String user_email,@Param("user_phone") String user_phone,@Param("user_IdCard") String user_IdCard,@Param("user_realName") String user_realName,@Param("user_id") String user_id);
    //根据用户名查询密码
    @Select("select user_password from user where user_name = #{user_name}")
    String getPasByName(String user_name);

}
