package com.yaner.house.bean;

/**
 * @author:sfq
 * @create 2019-04-16  17:16
 * 关联数据库bishe_house中的house表的bean
 **/
public class House {
    private int house_id;//房屋表主键
    private String house_sentType;//出租方式
    private String house_street;//所属街道
    private String house_xq;//小区信息
    private String house_roof;//楼层信息
    private String house_shape;//房屋户型
    private String house_area;//房屋面积
    private String house_money;//租金
    private String house_moneyType;//押金形式
    private String house_title;//租房标题
    private String house_flag;//房屋标签
    private String house_image;//房源图片
    private String house_state;//房屋状态：1代表正常-1代表禁用2代表被租用
    private String isApproval;//1代表审0代表等待审批,批通过-1代表审批未通过
    private String admin_issue;//发布房源的管理员ID

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getHouse_sentType() {
        return house_sentType;
    }

    public void setHouse_sentType(String house_sentType) {
        this.house_sentType = house_sentType;
    }

    public String getHouse_street() {
        return house_street;
    }

    public void setHouse_street(String house_street) {
        this.house_street = house_street;
    }

    public String getHouse_xq() {
        return house_xq;
    }

    public void setHouse_xq(String house_xq) {
        this.house_xq = house_xq;
    }

    public String getHouse_roof() {
        return house_roof;
    }

    public void setHouse_roof(String house_roof) {
        this.house_roof = house_roof;
    }

    public String getHouse_shape() {
        return house_shape;
    }

    public void setHouse_shape(String house_shape) {
        this.house_shape = house_shape;
    }

    public String getHouse_area() {
        return house_area;
    }

    public void setHouse_area(String house_area) {
        this.house_area = house_area;
    }

    public String getHouse_money() {
        return house_money;
    }

    public void setHouse_money(String house_money) {
        this.house_money = house_money;
    }

    public String getHouse_moneyType() {
        return house_moneyType;
    }

    public void setHouse_moneyType(String house_moneyType) {
        this.house_moneyType = house_moneyType;
    }

    public String getHouse_title() {
        return house_title;
    }

    public void setHouse_title(String house_title) {
        this.house_title = house_title;
    }

    public String getHouse_flag() {
        return house_flag;
    }

    public void setHouse_flag(String house_flag) {
        this.house_flag = house_flag;
    }

    public String getHouse_image() {
        return house_image;
    }

    public void setHouse_image(String house_image) {
        this.house_image = house_image;
    }

    public String getHouse_state() {
        return house_state;
    }

    public void setHouse_state(String house_state) {
        this.house_state = house_state;
    }

    public String getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(String isApproval) {
        this.isApproval = isApproval;
    }


    public String getAdmin_issue() {
        return admin_issue;
    }

    public void setAdmin_issue(String admin_issue) {
        this.admin_issue = admin_issue;
    }

    @Override
    public String toString() {
        return "House{" +
                "house_id=" + house_id +
                ", house_sentType='" + house_sentType + '\'' +
                ", house_street='" + house_street + '\'' +
                ", house_xq='" + house_xq + '\'' +
                ", house_roof='" + house_roof + '\'' +
                ", house_shape='" + house_shape + '\'' +
                ", house_area='" + house_area + '\'' +
                ", house_money=" + house_money +
                ", house_moneyType='" + house_moneyType + '\'' +
                ", house_title='" + house_title + '\'' +
                ", house_flag='" + house_flag + '\'' +
                ", house_image='" + house_image + '\'' +
                ", house_state=" + house_state +
                ", isApproval=" + isApproval +
                ", admin_issue=" + admin_issue +
                '}';
    }
}
