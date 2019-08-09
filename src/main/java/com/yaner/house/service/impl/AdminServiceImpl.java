package com.yaner.house.service.impl;

import com.alibaba.fastjson.JSON;
import com.yaner.house.bean.Admin;
import com.yaner.house.mapper.AdminMapper;
import com.yaner.house.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:50
 **/
@Service
public class AdminServiceImpl implements AdminService {
    //通过自动注入，生成adminMapper
    @Autowired
    private AdminMapper adminMapper;

    ////根据登陆保存的admin_id查询管理员信息并返回
    @Override
    public List<Admin> getAdminById(HttpSession session) {
        System.out.println("进入了IMPl");
        //调用AdminMapper 进入数据库查询信息，并返回 List<Admin>
        String admin_id= session.getAttribute("admin_id").toString();
        List<Admin> adminById = adminMapper.getAdminById(Integer.parseInt(admin_id));
        //String adminString = JSON.toJSONString(adminById);
        return adminById;
    }

    //查询所有未被禁用的员工包括管理员 :admin_state = 1
    @Override
    public List<Admin> getAdmins() {
        //由于管理员的权限在数据库中存储为数字，要转化为文字
        List<Admin> adminLists = adminMapper.getAdmins();
        for (int i = 0; i < adminLists.size(); i++) {
            if ("1".equals(adminLists.get(i).getAdmin_limit())) {
                adminLists.get(i).setAdmin_limit("普通");
            } else if ("2".equals(adminLists.get(i).getAdmin_limit())) {
                adminLists.get(i).setAdmin_limit("超级");
            }
            //数据转换完成
        }
        return adminLists;
    }

    //由管理员添加员工或者管理员
    @Override
    public String addAdmin(Admin admin) {
        //rteurnAddInfo AJAX返回的信息
        String rteurnAddInfo = "";
        //判断新增的用户名是否重复，进数据库查询所有的admin_name，循环判断。
        List<String> adminNameLists = adminMapper.getAllAdminNames();
        if (adminNameLists.contains(admin.getAdmin_name())) {
            System.out.println("用户名重复！");
            return rteurnAddInfo = "addAdmin002";//用户名重复！

        } else {

            //由于传递员工的权限的值为文字，必须转化为数字存入数据库
            if ("普通".equals(admin.getAdmin_limit())) {
                admin.setAdmin_limit("1");
            } else if ("超级".equals(admin.getAdmin_limit())) {
                admin.setAdmin_limit("2");
            }//数据转换完成
            System.out.println("添加的员工" + admin.toString());
            //存入数据库
            int addInfo = adminMapper.addAdmin(admin);
            if (addInfo > 0) {
                System.out.println("添加员工成功");
                return rteurnAddInfo = "addAdmin001";//添加成功
            }else{
                System.out.println("添加员工失败");
                return rteurnAddInfo = "addAdmin003";//添加失败
            }
        }

    }

    //修改员工信息（由员工自己修改）
    @Override
    public void updateAdminByMyself(Admin admin) {
        //修改数据库
        adminMapper.updateAdminByMyself(admin);
    }
    //禁用员工，普通超级都可以被禁用
    @Override
    public void deleteAdmin(Admin admin) {
        //修改数据库
        adminMapper.deleteAdmin(admin);
    }
    //修改员工
    @Override
    public void updateAdmin(Admin admin) {
        //由于传递员工的权限的值为文字，必须转化为数字存入数据库
        if("普通".equals(admin.getAdmin_limit())){
            admin.setAdmin_limit("1");
        }else if ("超级".equals(admin.getAdmin_limit())){
            admin.setAdmin_limit("2");
        }
        //存入数据库
        adminMapper.updateAdmin(admin);
    }

    @Override
    public String haveDealInfo01(HttpSession session) {
        //进入数据库查询
        int dealInfo = adminMapper.haveDealInfo01(session.getAttribute("admin_id").toString());
        if (dealInfo>0){
            //有需要处理的信息
            return "isHaveDealInfo001";//有需要处理的信息
        }else{
            return "isHaveDealInfo002";//没有需要处理的信息
        }
    }

    @Override
    public String haveDealInfo02() {
        int dealInfo = adminMapper.haveDealInfo02();
        if (dealInfo>0){
            //有需要处理的信息
            return "isHaveDealInfo001";//有需要处理的信息
        }else{
            return "isHaveDealInfo002";//没有需要处理的信息
        }
    }

}
