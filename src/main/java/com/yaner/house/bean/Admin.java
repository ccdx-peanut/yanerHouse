package com.yaner.house.bean;

/**
 * @author:sfq
 * @create 2019-04-16  17:16
 * 关联数据库bishe_house中的admin表的bean
 **/
public class Admin {
    private int admin_id;//管理员表主键
    private String admin_name;//管理员登录名,与登陆后展示一致
    private String admin_password;//管理员密码
    private String admin_email;//管理员邮箱
    private String admin_IdCard;//管理员身份证
    private String admin_phone;//管理员电话号码
    private String admin_limit;//1代表普通管理员,2代表超级管理员
    private String admin_state;//管理员状态,1代表正常,-1代表禁用

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_IdCard() {
        return admin_IdCard;
    }

    public void setAdmin_IdCard(String admin_IdCard) {
        this.admin_IdCard = admin_IdCard;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public String getAdmin_state() {
        return admin_state;
    }

    public void setAdmin_state(String admin_state) {
        this.admin_state = admin_state;
    }

    public String getAdmin_limit() {
        return admin_limit;
    }

    public void setAdmin_limit(String admin_limit) {
        this.admin_limit = admin_limit;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_email='" + admin_email + '\'' +
                ", admin_IdCard='" + admin_IdCard + '\'' +
                ", admin_phone='" + admin_phone + '\'' +
                ", admin_limit=" + admin_limit +
                ", admin_state=" + admin_state +
                '}';
    }
}
