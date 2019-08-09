package com.yaner.house.mapper;

import com.yaner.house.bean.RentMessage;
import org.apache.ibatis.annotations.*;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-05-22  15:58
 **/
@Mapper
public interface RmMapper {
    //生成等待用户确认订单
    @Insert("insert into RentMessage (rm_user_id,rm_house_id,rm_house_image,rent_date,return_date,rm_money,rm_yajin) values" +
            "(#{rm_user_id},#{rm_house_id},#{rm_house_image},#{rent_date},#{return_date},#{rm_money},#{rm_yajin})")
    int addRm(RentMessage rentMessage);

    //微信查询个人订单
    @Select("select *from rentmessage where rm_user_id = #{rm_user_id}")
    List<RentMessage> geyMyOrder(String rm_user_id);

    //微信取消订单
    @Delete("delete from rentmessage where rm_id = #{rm_id}")
    int quxiaoOrder(String rm_id);

    //微信确认订单
    @Update("update rentmessage set userArrearage = 1 where userArrearage = -1 and rm_id = #{rm_id} ")
    int querenOrder(String rm_id);

    //根据rm_id查询用户id
    @Select("select rm_user_id from rentmessage where rm_id = #{rm_id}")
    String getUserIdByRmId(String rm_id);

    //查询租赁时间
    @Select("select rent_date from rentmessage where rm_id = #{rm_id}")
    String getRentDateByRmId(String rm_id);

    //查询归还时间
    @Select("select return_date from rentmessage where rm_id = #{rm_id}")
    String getReturnDateByRmId(String rm_id);

    //退还租金
    @Update("update rentmessage set isArrearage = 1 where userArrearage = 1 and rm_id = #{rm_id} ")
    int tuizuOrder(String rm_id);

    //查询所有被用户同意支付的订单
    @Select("select * from rentmessage where userArrearage = 1 and isArrearage = -1")
    List<RentMessage> getRentMes();
}
