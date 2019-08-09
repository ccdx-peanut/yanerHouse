package com.yaner.house.bean;

/**
 * @author:sfq
 * @create 2019-04-17  9:38
 **/
public class RentMessage {
    private int rm_id;//租赁表主键
    private String  rm_user_id;//租赁房间的用户的ID
    private String rm_house_id;//租赁房间的ID
    private String rm_house_image;//租赁房间的ID
    private String rent_date;//租赁日期
    private String return_date;//归还日期
    private String rm_money;//花费金额
    private String rm_yajin;//押金
    private String isArrearage;//1代表系统未欠费-1代表欠费
    private String userArrearage;//用户确认订单

    public int getRm_id() {
        return rm_id;
    }

    public void setRm_id(int rm_id) {
        this.rm_id = rm_id;
    }

    public String getRm_user_id() {
        return rm_user_id;
    }

    public void setRm_user_id(String rm_user_id) {
        this.rm_user_id = rm_user_id;
    }

    public String getRm_house_id() {
        return rm_house_id;
    }

    public void setRm_house_id(String rm_house_id) {
        this.rm_house_id = rm_house_id;
    }



    public String getRm_house_image() {
        return rm_house_image;
    }

    public void setRm_house_image(String rm_house_image) {
        this.rm_house_image = rm_house_image;
    }

    public String getRent_date() {
        return rent_date;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getRm_money() {
        return rm_money;
    }

    public void setRm_money(String rm_money) {
        this.rm_money = rm_money;
    }

    public String getRm_yajin() {
        return rm_yajin;
    }

    public void setRm_yajin(String rm_yajin) {
        this.rm_yajin = rm_yajin;
    }

    public String getIsArrearage() {
        return isArrearage;
    }

    public void setIsArrearage(String isArrearage) {
        this.isArrearage = isArrearage;
    }


    public String getUserArrearage() {
        return userArrearage;
    }

    public void setUserArrearage(String userArrearage) {
        this.userArrearage = userArrearage;
    }

    @Override
    public String toString() {
        return "RentMessage{" +
                "rm_id=" + rm_id +
                ", rm_user_id='" + rm_user_id + '\'' +
                ", rm_house_id='" + rm_house_id + '\'' +
                ", rm_house_image='" + rm_house_image + '\'' +
                ", rent_date='" + rent_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", rm_money='" + rm_money + '\'' +
                ", rm_yajin='" + rm_yajin + '\'' +
                ", isArrearage='" + isArrearage + '\'' +
                ", userArrearage='" + userArrearage + '\'' +
                '}';
    }
}
