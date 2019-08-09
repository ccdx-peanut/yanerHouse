package com.yaner.house.service.impl;

import com.yaner.house.mapper.MoneyMapper;
import com.yaner.house.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:sfq
 * @create 2019-05-21  20:35
 **/
@Service
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    private MoneyMapper moneyMapper;
    @Override
    public String getBalanceByUserId(String user_id) {
        String balance = moneyMapper.getBalanceByUserId(user_id);
        return balance;
    }

    @Override
    public String myChongzhi(String user_id, String balance) {
        String balanceAdd4000 = (Integer.parseInt(balance)+4000)+"";
        System.out.println("充值的user_id:"+user_id+"-"+"充值的balanceAdd4000:"+balanceAdd4000);
        int aa = moneyMapper.myChongzhi(user_id,balanceAdd4000);
        if (aa > 0){
            return "balanceInfo001";//充值成功
        }else {
            return "balanceInfo002";//充值失败
        }

    }
}
