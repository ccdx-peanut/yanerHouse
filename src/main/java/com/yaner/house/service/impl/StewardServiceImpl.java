package com.yaner.house.service.impl;

import com.yaner.house.bean.Steward;
import com.yaner.house.mapper.StewardMapper;
import com.yaner.house.service.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author:sfq
 * @create 2019-04-16  17:51
 **/
@Service
public class StewardServiceImpl implements StewardService {
    //自动注入StewardMapper
    @Autowired
    private StewardMapper stewardMapper;
    //添加管家
    @Override
    public void addSteward(Steward steward) {
        //进入数据库添加信息
        stewardMapper.addSteward(steward);
    }
    //修改管家byid
    @Override
    public void updateSteward(Steward steward) {
        //传进来的数据由于状态不是数字表示，所以进行处理
        if ("正常".equals(steward.getSte_state())){
            steward.setSte_state("1");
        }else if("禁用".equals(steward.getSte_state())){
            steward.setSte_state("-1");
        }
        //进入数据库修改信息
        stewardMapper.updateSteward(steward);
    }
    //禁用管家
    @Override
    public void deleteSteward(Steward steward) {
        //进入数据库修改
        stewardMapper.deleteSteward(steward);
    }
    //查询管家信息
    @Override
    public List<Steward> getAllStewards() {
        //System.out.println("---------------------");
        //数据库查询并返回
         List<Steward> list = stewardMapper.getAllStewards();
        System.out.println("查出来的list长度:"+list.size());
         //处理state：1->正常 -1->禁用
        for (int i =0;i<list.size();i++){
            if ("1".equals(list.get(i).getSte_state())){
                list.get(i).setSte_state("正常");
            }else{
                list.get(i).setSte_state("禁用");
            }
        }
         return list;
    }
    //微信查询随机一个的管家信息
    @Override
    public Steward WXGetOneSteward() {
        List<Steward> lists = stewardMapper.getAllStewards();

        int i = (int)(1+Math.random()*(2-1+1));
        Steward steward =  lists.get(i);
        return steward;
    }
}
