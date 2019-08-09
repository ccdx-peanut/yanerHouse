package com.yaner.house.bean;

/**
 * @author:sfq
 * @create 2019-04-16  17:17
 * 关联数据库bishe_house中的steward表的bean
 **/
public class Steward {
    private int ste_id;//管家表主键
    private String ste_name;//管家姓名
    private String ste_six;//管家性别
    private String ste_IdCard;//管家身份证
    private String ste_phone;//管家手机号
    private String ste_state;//管家状态：1代表正常0代表禁用

    public int getSte_id() {
        return ste_id;
    }

    public void setSte_id(int ste_id) {
        this.ste_id = ste_id;
    }

    public String getSte_name() {
        return ste_name;
    }

    public void setSte_name(String ste_name) {
        this.ste_name = ste_name;
    }

    public String getSte_six() {
        return ste_six;
    }

    public void setSte_six(String ste_six) {
        this.ste_six = ste_six;
    }

    public String getSte_IdCard() {
        return ste_IdCard;
    }

    public void setSte_IdCard(String ste_IdCard) {
        this.ste_IdCard = ste_IdCard;
    }

    public String getSte_phone() {
        return ste_phone;
    }

    public void setSte_phone(String ste_phone) {
        this.ste_phone = ste_phone;
    }

    public String getSte_state() {
        return ste_state;
    }

    public void setSte_state(String ste_state) {
        this.ste_state = ste_state;
    }

    @Override
    public String toString() {
        return "steward{" +
                "ste_id=" + ste_id +
                ", ste_name='" + ste_name + '\'' +
                ", ste_six='" + ste_six + '\'' +
                ", ste_IdCard='" + ste_IdCard + '\'' +
                ", ste_phone='" + ste_phone + '\'' +
                ", ste_state=" + ste_state +
                '}';
    }
}
