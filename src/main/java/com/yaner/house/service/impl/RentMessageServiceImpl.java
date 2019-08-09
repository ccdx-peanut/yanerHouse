package com.yaner.house.service.impl;

import com.yaner.house.bean.RentMessage;
import com.yaner.house.mapper.HouseMapper;
import com.yaner.house.mapper.MoneyMapper;
import com.yaner.house.mapper.RmMapper;
import com.yaner.house.service.RentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-05-22  16:04
 **/
@Service
public class RentMessageServiceImpl implements RentMessageService {
    @Autowired
    private RmMapper rmMapper;
    @Autowired
    private MoneyMapper moneyMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public String addRm(String user_id, String house_id, String rm_house_image, String house_money, String house_moneyType) {
        int rm_money ;//租金
        int rm_yajin;//押金
        String return_date;//归还时间
        RentMessage rentMessage = new RentMessage();
        //获取当前时间
        //开始租赁

        //处理归还时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //处理租赁时间
        Date date02 = new Date();
        String rent_date = format.format(date02);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        //处理花费的总金额（只计算租金 不算押金）
         if("押一付一".equals(house_moneyType)){
            rm_yajin = Integer.parseInt(house_money);
            rm_money = Integer.parseInt(house_money);
            cal.add(Calendar.MONTH, 1);//增加一个月 
            return_date = format.format(cal.getTime());
        } else if("押一付二".equals(house_moneyType)){
            rm_yajin = Integer.parseInt(house_money);
            rm_money = 2*(Integer.parseInt(house_money));
            cal.add(Calendar.MONTH, 2);//增加2个月 
            return_date = format.format(cal.getTime());
        }else if("押一付三".equals(house_moneyType)){
            rm_yajin = Integer.parseInt(house_money);
            rm_money = 3*(Integer.parseInt(house_money));
            cal.add(Calendar.MONTH, 3);//增加3个月 
            return_date = format.format(cal.getTime());
        }else{//无押金
            rm_yajin = 0;
            rm_money = Integer.parseInt(house_money);
            cal.add(Calendar.MONTH, 1);//增加一个月 
            return_date = format.format(cal.getTime());
        }
        String  rm_money02 = rm_money+"";
        String  rm_yajin02 = rm_yajin+"";

        //数据填充
        rentMessage.setRm_user_id(user_id);
        rentMessage.setRm_house_id(house_id);
        rentMessage.setRm_house_image(rm_house_image);
        rentMessage.setRent_date(rent_date);
        rentMessage.setReturn_date(return_date);
        rentMessage.setRm_money(rm_money02);
        rentMessage.setRm_yajin(rm_yajin02);

        System.out.println(rentMessage.toString());
        int i  = rmMapper.addRm(rentMessage);
        if (i>0){
            return "addRm001";
        }else{
            return "addRm002";
        }

    }

    @Override
    public List<RentMessage> geyMyOrder(String rm_user_id) {

        List<RentMessage> list = rmMapper.geyMyOrder(rm_user_id);
        if (list == null) {

        }else {
            for (int i = 0; i < list.size(); i++) {
                    if("1".equals(list.get(i).getUserArrearage())){
                        list.get(i).setUserArrearage("已确认");
                    }else {
                        list.get(i).setUserArrearage("未确认");
                    }
                    if ("1".equals(list.get(i).getIsArrearage())){
                        list.get(i).setIsArrearage("已归还租金");
                    }else{
                        list.get(i).setIsArrearage("未归还租金");
                    }
            }
        }
        return list;
    }
    //微信确认订单
    @Override
    public String querenOrder(String rm_id, String rm_money, String rm_yajin, String rm_house_id) {
        String user_id = rmMapper.getUserIdByRmId(rm_id);
        System.out.println("user_id"+user_id);
        String balance = moneyMapper.getBalanceByUserId(user_id);
        System.out.println("balance"+balance);

        if (Integer.parseInt(balance)>= Integer.parseInt(rm_money)+Integer.parseInt(rm_yajin)){
            //扣费完成的余额
            String balance02 = (Integer.parseInt(balance)- Integer.parseInt(rm_money)-Integer.parseInt(rm_yajin))+"";
            int a = moneyMapper.updateBalance(user_id,balance02);
            if (a>0){
                //房源状态修改为2
                int b = houseMapper.houseState02(rm_house_id);
                if (b>0){
                    //用户确认
                    int c = rmMapper.querenOrder(rm_id);
                    if (c>0){
                        return "querenOrder001";
                    }else {
                        return "querenOrder003";
                    }
                }else {
                    return "querenOrder003";
                }

            }else{
                return "querenOrder003";
            }

        }else {
            return "querenOrder002";
        }

    }
    //微信取消订单
    @Override
    public String quxiaoOrder(String rm_id) {
        int i = rmMapper.quxiaoOrder(rm_id);
        if (i>0){
            return "quxiaoOrder001";//取消订单成功！
        }else {
            return "quxiaoOrder002";//取消订单失败！
        }

    }

    @Override
    public String tuizuOrder(String rm_id, String rm_house_id) {
        //查询租赁时间
//        String rent_date = rmMapper.getRentDateByRmId(rm_id);
        String rent_date;//租赁时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        rent_date = format.format(date);
        //查询归还时间
        String return_date = rmMapper.getReturnDateByRmId(rm_id);
        String user_id;
        System.out.println("相差的天数:"+getDaySub(rent_date,return_date));
        if(getDaySub(rent_date,return_date)<30){
        // 不足一个月，直接退租
            houseMapper.houseState01(rm_house_id);
            rmMapper.tuizuOrder(rm_id);
            return "tuizuOrder001";
        } else if (getDaySub(rent_date,return_date)>29 && getDaySub(rent_date,return_date)<62){
            //说明剩余天数大于一个月，要退租金
            //改变房源状态
            houseMapper.houseState01(rm_house_id);
            //获取用户ID
            user_id = rmMapper.getUserIdByRmId(rm_id);
            //获取余额
            String balance = moneyMapper.getBalanceByUserId(user_id);
            //获取租金
            String house_money = houseMapper.getMoneyByHouseId(rm_house_id);
            //返还之后的余额
            String balance02 = (Integer.parseInt(balance)+Integer.parseInt(house_money))+"";
            //更改余额
            moneyMapper.updateBalance(user_id,balance02);
            //退租 isArrearage = 1
            rmMapper.tuizuOrder(rm_id);
            return "tuizuOrder001";
        }else if (getDaySub(rent_date,return_date)>=62 ){
            //说明剩余天数大于2个月，要退租金
            //改变房源状态
            houseMapper.houseState01(rm_house_id);
            //获取用户ID
            user_id = rmMapper.getUserIdByRmId(rm_id);
            //获取余额
            String balance = moneyMapper.getBalanceByUserId(user_id);
            //获取租金
            String house_money = houseMapper.getMoneyByHouseId(rm_house_id);
            System.out.println("租金:"+house_money);
            //返还之后的余额
            String balance02 = (Integer.parseInt(balance)+2*Integer.parseInt(house_money))+"";
            System.out.println("返还之后的余额:"+balance02);
            //更改余额
            moneyMapper.updateBalance(user_id,balance02);
            //退租 isArrearage = 1
            rmMapper.tuizuOrder(rm_id);
            return "tuizuOrder001";
        }else{
            return "tuizuOrder002";
        }

    }

    @Override
    public List<RentMessage> getRentMes() {
        return rmMapper.getRentMes();
    }

    //日期做差工具
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate;
        java.util.Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
        } catch (Exception e)
        {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return day;
    }

}
