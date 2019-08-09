package com.yaner.house.controller;

import com.yaner.house.bean.RentMessage;
import com.yaner.house.service.RentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-05-22  15:14
 **/
@RestController
public class RentMessageController {
    @Autowired
    private RentMessageService rentMessageService;

    //微信添加订单
    @RequestMapping("/WXRentHouse")
    public String WXRentHouse(HttpServletRequest request){
        System.out.println("进入了WXRentHouse的Controller");
        String user_id = request.getParameter("rm_user_id");
        String house_id = request.getParameter("rm_house_id");
        String rm_house_image = request.getParameter("rm_house_image");
        String house_money = request.getParameter("house_money");
        String house_moneyType = request.getParameter("house_moneyType");

        System.out.println("rm_user_id："+user_id);
        System.out.println("rm_house_id："+house_id);
        System.out.println("rm_house_image："+rm_house_image);
        System.out.println("house_money："+house_money);
        System.out.println("house_moneyType："+house_moneyType);
        return rentMessageService.addRm( user_id, house_id, rm_house_image, house_money, house_moneyType);

    }
    //微信搜索房源模糊查询
    @RequestMapping("/geyMyOrder")
    public List<RentMessage> geyMyOrder(HttpServletRequest request) {
        System.out.println("进入了geyMyOrder的Controller");
        String rm_user_id = request.getParameter("user_id");
        System.out.println("rm_user_id:"+rm_user_id);
        return rentMessageService.geyMyOrder(rm_user_id);
    }
    //微信确认订单
    @RequestMapping("/querenOrder")
    public String querenOrder(HttpServletRequest request) {
        System.out.println("进入了querenOrder的Controller");
        String rm_id = request.getParameter("rm_id");
        String rm_money = request.getParameter("rm_money");
        String rm_yajin = request.getParameter("rm_yajin");
        String rm_house_id = request.getParameter("rm_house_id");
        System.out.println("rm_id:"+rm_id);
        System.out.println("rm_money:"+rm_money);
        System.out.println("rm_yajin:"+rm_yajin);
        System.out.println("rm_house_id:"+rm_house_id);
        return rentMessageService.querenOrder(rm_id,rm_money,rm_yajin,rm_house_id);

    }
    //微信取消订单
    @RequestMapping("/quxiaoOrder")
    public String quxiaoOrder(HttpServletRequest request) {
        System.out.println("进入了querenOrder的Controller");
        String rm_id = request.getParameter("rm_id");

        System.out.println("rm_id:"+rm_id);
        return rentMessageService.quxiaoOrder(rm_id);

    }
    //微信退租
    @RequestMapping("/tuizuOrder")
    public String tuizuOrder(HttpServletRequest request) {
        System.out.println("进入了tuizuOrder的Controller");
        String rm_id = request.getParameter("rm_id");
        String rm_house_id = request.getParameter("rm_house_id");
        System.out.println("rm_id:"+rm_id);
        System.out.println("rm_house_id:"+rm_house_id);
        return rentMessageService.tuizuOrder(rm_id,rm_house_id);
    }
    //查询所有被用户同意支付的订单
    @RequestMapping("/getRentMes")
    public List<RentMessage> getRentMes() {
        System.out.println("进入了getRentMes的Controller");
        return rentMessageService.getRentMes();
    }
    //退还租金
    @RequestMapping("/zujinReturn")
    public String zujinReturn(RentMessage rentMessage) {
        System.out.println("进入了mYTuizu的Controller");
        String rm_id = rentMessage.getRm_id()+"";
        String rm_house_id = rentMessage.getRm_house_id();
        System.out.println("rm_id:"+rm_id);
        System.out.println("rm_house_id:"+rm_house_id);
        return rentMessageService.tuizuOrder(rm_id,rm_house_id);
    }
}
