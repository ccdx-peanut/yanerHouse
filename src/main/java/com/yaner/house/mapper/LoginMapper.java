package com.yaner.house.mapper;

import com.yaner.house.bean.Admin;
import com.yaner.house.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-23  9:40
 **/
@Mapper
public interface LoginMapper {
    //查询登陆的用户名与密码是否匹配数据库
    @Select("select * from admin where admin_name= #{admin_name} and admin_password= #{admin_password}")
    Admin loginJudge(@Param("admin_name") String admin_name,@Param("admin_password") String admin_password);
    //小程序查询登陆的用户名与密码是否匹配数据库
    @Select("select * from user where user_name= #{user_name} and user_password= #{user_password}")
    User WXloginJudge(@Param("user_name") String user_name, @Param("user_password") String user_password);
    //搜索登录时的user_id
    @Select("select user_id from user where user_name = #{user_name} and user_password= #{user_password}")
    String WXLoginUserId(@Param("user_name") String user_name, @Param("user_password") String user_password);

}
