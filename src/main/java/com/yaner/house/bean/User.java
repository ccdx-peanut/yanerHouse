package com.yaner.house.bean;

/**
 * @author:sfq
 * @create 2019-04-16  17:16
 * 关联数据库bishe_house中的user表的bean
 **/
public class User {
    private int user_id;//用户表主键
    //private int user_limit;//用户权限描述,1代表普通用户,2代表管理员
    private String user_name;//用户名
    private String user_password;//用户密码
    private String user_email;//用户邮箱
    private String user_six;//用户性别,1代表男,2代表女
    private String user_phone;//用户手机号
    private String user_IdCard;//用户身份证号
    private String user_realName;//用户真实姓名,登陆后展示
    private String user_state;//用户状态：1代表正常,-1代表禁用

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_six() {
        return user_six;
    }

    public void setUser_six(String user_six) {
        this.user_six = user_six;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_IdCard() {
        return user_IdCard;
    }

    public void setUser_IdCard(String user_IdCard) {
        this.user_IdCard = user_IdCard;
    }

    public String getUser_realName() {
        return user_realName;
    }

    public void setUser_realName(String user_realName) {
        this.user_realName = user_realName;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    @Override
    public String toString() {
        return "user{" +
                "user_id=" + user_id +  '\''+
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_six=" + user_six +
                ", user_phone='" + user_phone + '\'' +
                ", user_IdCard='" + user_IdCard + '\'' +
                ", user_realName='" + user_realName + '\'' +
                ", user_state=" + user_state +
                '}';
    }
}
