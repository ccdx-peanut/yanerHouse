package com.yaner.house.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author:sfq
 * @create 2019-05-21  20:34
 **/
@Service
public interface MoneyService {
    //查询余额
    String getBalanceByUserId(@Param("user_id") String user_id);

    //进行余额充值 或者 扣费处理
    String myChongzhi(@Param("user_id") String user_id,@Param("balance") String balance);
}
