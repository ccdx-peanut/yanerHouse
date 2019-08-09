package com.yaner.house.controller;

import com.yaner.house.bean.Steward;
import com.yaner.house.service.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:46
 * 管家模块的Controller
 **/
@RestController
public class StewardController {
    //自动注入sercive
    @Autowired
    private StewardService stewardService;

    //查询所有的管家信息
    @RequestMapping("/getAllStewards")
    public List<Steward> getAllStewards(){
        System.out.println("查询");
       return stewardService.getAllStewards();
    }

    //修改管家的信息byid
    @RequestMapping("/updateSteWard")
    public void updateSteward(Steward steward){
        System.out.println("修改:"+steward.toString());
        stewardService.updateSteward(steward);
    }

    //禁用管家byid ste_state=-1
    @RequestMapping("/deleteSteward")
    public void deleteSteward(Steward steward) {
        System.out.println("禁用:"+steward.toString());
        stewardService.deleteSteward(steward);
    }

    //添加管家
    @RequestMapping("/addSteward")
    public void addSteward(Steward steward){
        //System.out.println("添加:"+steward.toString());
        stewardService.addSteward(steward);
    }

    //微信查询随机一个的管家信息
    @RequestMapping("/WXGetOneSteward")
    public Steward WXGetOneSteward(){
        System.out.println("查询WXGetOneSteward");
        return stewardService.WXGetOneSteward();
    }
}
