package com.yaner.house.mapper;

import com.yaner.house.bean.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:48
 **/
@Mapper
public interface AdminMapper {
    //根据登陆保存的admin_id查询管理员信息并返回
    /*@Select("select * from admin where admin_id= #{admin_id}")
    List<Admin> getAdminById(@Param("admin_id") int admin_id);*/

    //查询员工信息BY ID
    @Select("select * from admin where admin_id= #{admin_id}")
    List<Admin> getAdminById(@Param("admin_id") int admin_id);

    //查询所有未被禁用的员工包括管理员 :admin_state = 1
    @Select("select * from admin where admin_state = 1")
    List<Admin> getAdmins();

    //由管理员添加员工或者管理员
    @Insert("insert into admin (admin_name,admin_password,admin_email,admin_IdCard,admin_phone,admin_limit) values " +
            "(#{admin_name},#{admin_password},#{admin_email},#{admin_IdCard},#{admin_phone}, #{admin_limit})")
    int addAdmin(Admin admin);

    //修改员工信息（由员工自己修改）
    @Update("update admin set admin_name=#{admin_name},admin_password=#{admin_password},admin_email=#{admin_email},admin_IdCard=#{admin_IdCard}," +
            "admin_phone=#{admin_phone} where admin_id= #{admin_id}")
    void updateAdminByMyself(Admin admin);

    //禁用员工，普通超级都可以被禁用
    @Update("update admin set admin_state = -1 where admin_id = #{admin_id}")
    void deleteAdmin(Admin admin);

    //修改员工
    @Update("update admin set admin_name=#{admin_name},admin_password=#{admin_password},admin_email=#{admin_email},admin_IdCard=#{admin_IdCard}," +
            "admin_phone=#{admin_phone} where admin_id= #{admin_id}")
    void updateAdmin(Admin admin);

    //查询所有的员工的用户名，新增员工时使用
    @Select("select admin_name from admin ")
    List<String> getAllAdminNames();

    //查询员工是否有上线失败的信息
    @Select("select count(*) from house where admin_issue = #{admin_issue} and isApproval = -1")
    int haveDealInfo01(@Param("admin_issue") String admin_issue);

    //查询管理员是否有未处理的审批信息
    @Select("select count(*) from house where isApproval = 0")
    int haveDealInfo02();

    //查询admin信息by admin_name
    @Select("select * from admin where admin_name = #{admin_name}")
    Admin getAdminByAdminName(@Param("admin_name") String admin_name);
}
