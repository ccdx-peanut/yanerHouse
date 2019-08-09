package com.yaner.house.service;

import com.yaner.house.bean.House;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:49
 **/
@Service
public interface HouseService {
    //添加房源
    String addHouse(House house, HttpSession session);

    //根据发布人的ID 查询出所有由该ID发布的信息
    List<House> getMyIssuesById(HttpSession httpSession);

    //得到所有需要审批的信息与房屋状态为正常的
    List<House> getApprovals();

    //审批通过功能
    String agreeApproval(House house);

    //审批拒绝功能
    String refuseApproval(House house);

    //重新上线查询信息BY`admin_issue`
    List<House> getAgainIssuesByAdminIssue(HttpSession httpSession);

    //重新发布
    String againIssue(House house);

    //查询所有通过核审的房源与房源状态为1
    List<House> getHouses();

    //修改房源（修改上线的房源）
    String updateHouse(House house);

    //下架房源(只能下架已经上线的房源)
    String deleteHouse(House house);

    //微信查询所有通过核审的房源与房源状态为1
    List<House> WXGetHouses();

    //微信搜索房源模糊查询
    List<House> WXGetHousesBySearch(String queryName);

}
