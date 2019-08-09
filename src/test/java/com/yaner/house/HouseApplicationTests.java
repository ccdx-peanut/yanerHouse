package com.yaner.house;

import com.yaner.house.bean.Admin;
import com.yaner.house.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseApplicationTests {
    @Autowired
    private AdminService adminService;
    @Test
    public void contextLoads() {
        /*Admin admin = adminService.getAdminById(1);
        System.out.println(admin);*/
//        List<Admin> list = new ArrayList<Admin>();
//        if (list ==null){
//            System.out.println("111");
//        }else{
//            System.out.println("2222");
//        for (int a = 0;a<30;a++) {
//            int i = (int) (1 + Math.random() * (3 - 1 + 1));
//            System.out.println(i);
//        }
//        }
       // 获取时间加一年或加一月或加一天
//Date date = new Date();
//
//
////        date = (date)rent_date;
//Calendar cal = Calendar.getInstance();
//cal.setTime(date);//设置起时间
//System.out.println("111111111::::"+cal.getTime());
//cal.add(Calendar.YEAR, 1);//增加一年
//        System.out.println("输出::"+cal.getTime());
//cal.add(Calendar.DATE, 1);//增加一天  
//        System.out.println("输出::"+cal.getTime());
//cal.add(Calendar.DATE, -10);//减10天  
//        System.out.println("输出::"+cal.getTime());
//cal.add(Calendar.MONTH, 1);//增加一个月   
//
//System.out.println("输出::"+cal.getTime());
//        SimpleDateFormat rent_date = new SimpleDateFormat("yyyy-MM-dd");
//         String aa = rent_date.format(cal.getTime());
//        System.out.println("aa:"+aa);
//

    }

}
