package com.yaner.house.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author:sfq
 * @create 2019-04-29  10:12
 * 余额Mapper
 **/
@Mapper
public interface MoneyMapper {
    //添加余额的信息，在注册用户时，自动生成余额为0
    @Insert("insert into money (user_id) values (#{user_id})")
    int addMoneyRecord(String user_id);
    //进行余额充值
    @Update("update money set balance = #{balanceAdd4000} where user_id = #{user_id}")
    int myChongzhi(@Param("user_id") String user_id,@Param("balanceAdd4000") String balanceAdd4000);
    //查询余额
    @Select("select balance from money where user_id = #{user_id}")
    String getBalanceByUserId(@Param("user_id") String user_id);
    //进行余额修改
    @Update("update money set balance = #{balancePara} where user_id = #{user_id}")
    int updateBalance(@Param("user_id") String user_id,@Param("balancePara") String balancePara);
}
