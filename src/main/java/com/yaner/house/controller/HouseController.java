package com.yaner.house.controller;

import com.yaner.house.bean.House;
import com.yaner.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:46
 **/
@RestController
public class HouseController {
    //自动注入
    @Autowired
    private HouseService houseService;

    //添加房源
    @RequestMapping("/addHouse")
    public String addHouse(House house, HttpSession session){
        System.out.println("进入了添加房源HouseController");
        System.out.println(house.toString());

        String aa = houseService.addHouse(house,session);
        System.out.println("返回给AJAX的信息:"+aa);
        return aa;
    }

    //根据发布人的ID 查询出所有由该ID发布的信息
    @RequestMapping("/getMyIssuesById")
    public List<House> getMyIssuesById(HttpSession httpSession){
        List<House> list = houseService.getMyIssuesById(httpSession);
        System.out.println("查询出的我的发布的信息："+list.toString());
        return list;
    }

    //得到所有需要审批的信息与房屋状态为正常的
    @RequestMapping("/getApprovals")
    public List<House> getApprovals(){
        List<House> list = houseService.getApprovals();
        System.out.println("查询出我要审核的信息："+list.toString());
        return list;
    }

    //审批通过功能
    @RequestMapping("/agreeApproval")
    public String agreeApproval(House house){
       return houseService.agreeApproval(house);
    }

    //审批拒绝功能
    @RequestMapping("/refuseApproval")
    public String refuseApproval(House house){
        return houseService.refuseApproval(house);
    }

    //重新上线查询信息BY`admin_issue`
    @RequestMapping("/getAgainIssuesByAdminIssue")
    public List<House> getAgainIssuesByAdminIssue(HttpSession session){
        List<House> list = houseService.getAgainIssuesByAdminIssue(session);
        System.out.println("查询出我要重新上线的信息："+list.toString());
        return list;
    }

    //重新发布
    @RequestMapping("/againIssue")
    public String againIssue(House house){
        System.out.println("重新上线修改过后的数据："+house.toString());
        String aa = houseService.againIssue(house);
        System.out.println("返回给重新发布的AJAX的信息："+aa);
        return aa;
    }
    //查询所有通过核审的房源
    //要改！！！！ 房源状态必须为 1
    @RequestMapping("/getHouses")
    public List<House> getHouses(){
        System.out.println("进入了getHouses的Controller");
        List<House> list = houseService.getHouses();
        System.out.println("上线的房源的信息："+list.toString());
        return list;
    }
    //修改房源（修改上线的房源）
    @RequestMapping("/updateHouse")
    public String updateHouse(House house){
        System.out.println("进入了updateHouse的Controller");
        return houseService.updateHouse(house);
    }
    //下架房源(只能下架已经上线的房源)
    @RequestMapping("/deleteHouse")
    public String deleteHouse(House house){
        System.out.println("进入了deleteHouse的Controller");
        return houseService.deleteHouse(house);
    }
    //微信查询所有通过核审的房源与房源状态为1
    @RequestMapping("/WXGetHouses")
    public List<House> WXGetHouses(){
        System.out.println("进入了WXGetHouses的Controller");
        List<House> list = houseService.WXGetHouses();
        System.out.println("上线的房源的信息："+list.toString());
        return list;
    }
    //微信搜索房源模糊查询
    @RequestMapping("/WXGetHousesBySearch")
    public List<House> WXGetHousesBySearch(HttpServletRequest request){
        System.out.println("进入了WXGetHousesBySearch的Controller");
        String queryName = request.getParameter("queryName");
        System.out.println("queryName："+queryName);
        List<House> list = houseService.WXGetHousesBySearch(queryName);
        System.out.println("模糊查询上线的房源的信息："+list.toString());
        return list;
    }

}
