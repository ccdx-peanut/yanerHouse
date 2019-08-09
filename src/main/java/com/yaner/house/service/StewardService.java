package com.yaner.house.service;

import com.yaner.house.bean.Steward;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:50
 **/
@Service
public interface StewardService {
    //添加管家
    void addSteward(Steward steward);
    //修改管家byid
    void updateSteward(Steward steward);
    //禁用管家(ste_state = -1)
    void deleteSteward(Steward steward);
    //查询所有的管家的信息
    List<Steward> getAllStewards();
    //微信查询随机一个的管家信息
    Steward WXGetOneSteward();
}
