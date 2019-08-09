package com.yaner.house.controller;

import com.yaner.house.bean.Admin;
import com.yaner.house.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:46
 **/
@RestController
public class AdminController {
    //通过自动注入生成AdminService
    @Autowired
    private AdminService adminService;

    //查询员工信息BY ID
    @RequestMapping("/getAdminById")
    //@GetMapping("/getAdminById")
    public List<Admin> getAdminById(HttpSession httpSession/*HttpServletRequest request*/){
        System.out.println("进入了查询员工getAdminById方法");
        //模仿假数据，正常从登陆的session取值

        //int admin_id = 1;
        //返回查询的数据
        System.out.println(adminService.getAdminById(httpSession));
        return adminService.getAdminById(httpSession);
    }
    //查询该员工是否有需要处理的信息
    @RequestMapping("/haveDealInfo01")
    public String haveDealInfo01(HttpSession session){
        return adminService.haveDealInfo01(session);
    }
    //以上为员工的功能Controller
   /*-----------------------------------------------------------------------*/
    @GetMapping("/getHello")
    public String getHello(){
        System.out.println("--------------");
        return "hello";
    }


    //修改员工信息（由员工自己或者管理员修改）
    @RequestMapping("/updateAdminByMyself")
    public void updateAdminByMyself(Admin admin){
        System.out.println("修改员工信息");
        adminService.updateAdminByMyself(admin);
    }
    //中间为员工管理员共有的功能
    /*---------------------------------------------------------------------------*/
    //下面为管理员的功能
    //查询所有的未被禁用的员工包括管理员
    @RequestMapping("/getAdmins")
    public List<Admin> getAdmins(){
        System.out.println("查询所有未被禁用的员工");
        return adminService.getAdmins();
    }

    @RequestMapping("/deleteAdmin")
    //禁用员工，普通超级都可以被禁用
    public void deleteAdmin(Admin admin){
        System.out.println("禁用员工");
        adminService.deleteAdmin(admin);
    }

    @RequestMapping("/addAdmin")
    //添加员工
    public String addAdmin(Admin admin){
        System.out.println("添加员工");
        return adminService.addAdmin(admin);
    }

    @RequestMapping("/updateAdmin")
    //修改员工
    public void updateAdmin(Admin admin){
        System.out.println("修改员工");
        adminService.updateAdmin(admin);

    }

    //查询该管理员是否有需要处理的信息
    @RequestMapping("/haveDealInfo02")
    public String haveDealInfo02(HttpSession session){
        return adminService.haveDealInfo02();
    }

}
