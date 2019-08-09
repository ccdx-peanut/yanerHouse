package com.yaner.house.service;

import com.yaner.house.bean.RentMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-05-22  16:04
 **/
@Service
public interface RentMessageService {
    //微信添加订单
    String addRm(String user_id, String house_id,String rm_house_image,String house_money,String house_moneyType);

    //微信查询个人订单
    List<RentMessage> geyMyOrder(String rm_user_id);

    //微信确认订单
    String querenOrder(String rm_id,String rm_money,String rm_yajin,String rm_house_id);

    //微信取消订单
    String quxiaoOrder(String rm_id);

    //微信退租
    String tuizuOrder(String rm_id,String rm_house_id);

    //查询所有被用户同意支付的订单
    List<RentMessage> getRentMes();

}
