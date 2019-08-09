package com.yaner.house.service;

import com.yaner.house.bean.Admin;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:49
 **/
@Service
public interface AdminService {
    //根据登陆保存的admin_id查询管理员信息并返回
    List<Admin> getAdminById(HttpSession session);

    //查询所有未被禁用的员工包括管理员
    List<Admin> getAdmins();

    //由管理员添加员工或者管理员
    String addAdmin(Admin admin);

    //修改员工信息（由员工自己修改）
    void updateAdminByMyself(Admin admin);

    //禁用员工，普通超级都可以被禁用
    void deleteAdmin(Admin admin);

    //修改员工
    void updateAdmin(Admin admin);

    //查询员工是否有上线失败的信息
    String haveDealInfo01(HttpSession session);

    //查询管理员是否有未处理的审批信息
    String haveDealInfo02();

}
