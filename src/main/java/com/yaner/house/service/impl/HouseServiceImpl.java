package com.yaner.house.service.impl;

import com.yaner.house.bean.House;
import com.yaner.house.mapper.HouseMapper;
import com.yaner.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author:sfq
 * @create 2019-04-16  17:51
 **/
@Service
public class HouseServiceImpl implements HouseService {
    //自动注入
    @Autowired
    private HouseMapper houseMapper;

    //添加房源
    @Override
    public String addHouse(House house, HttpSession session) {
        //取到登陆的员工的ID
        String admin_issue = session.getAttribute("admin_id").toString();
        house.setAdmin_issue(admin_issue);
        String returnHouseInfo="";
        HouseTools(house);
        System.out.println(house.toString());
        String aa = houseImage(house.getHouse_image());
        house.setHouse_image(aa);
        int i = houseMapper.addHouse(house);
        if (i>0){
            return returnHouseInfo="addHouse001";
        }else{
            return returnHouseInfo="addHouse002";
        }

    }

    //根据发布人的ID 查询出所有由该ID发布的信息 参数admin_issue在 session中获取admin_id即可
    @Override
    public List<House> getMyIssuesById(HttpSession session) {
        String admin_issue = session.getAttribute("admin_id").toString();

        List<House> list = houseMapper.getMyIssuesById(admin_issue);
        for (int i = 0; i<list.size();i++){
            HouseTools(list.get(i));
            HouseStateTool(list.get(i));
        }
        return list;
    }

    //得到所有需要审批的信息与房屋状态为正常的
    @Override
    public List<House> getApprovals() {
        List<House> list = houseMapper.getApprovals();
        for (int i = 0; i<list.size();i++){
            HouseTools(list.get(i));
            HouseStateTool(list.get(i));
        }
        return list;
    }

    //审批通过功能
    @Override
    public String agreeApproval(House house) {
        int returnApprovalInfo = houseMapper.agreeApproval( house);
        if (returnApprovalInfo>0){
            return "approvalInfo001";//操作成功
        }else {
            return "approvalInfo002";//操作是失败
        }
    }

    //审批拒绝功能
    @Override
    public String refuseApproval(House house) {
        int returnApprovalInfo = houseMapper.refuseApproval(house);
        if (returnApprovalInfo>0){
            return "approvalInfo001";//操作成功
        }else {
            return "approvalInfo002";//操作是失败
        }
    }
    //重新上线查询信息
    @Override
    public List<House> getAgainIssuesByAdminIssue(HttpSession session) {
        String admin_issue = session.getAttribute("admin_id").toString();
        List<House> list = houseMapper.getAgainIssuesByAdminIssue(admin_issue);
        for (int i = 0; i<list.size();i++){
            HouseDateToPageTools(list.get(i));
            HouseStateTool(list.get(i));
        }
        return list;
    }
    //重新发布
    @Override
    public String againIssue(House house) {
        //重新发布 要先删除之前的图片 先查图片
       /* String house_image = houseMapper.getImageByHouseId(house.getHouse_id());
        String filePathPre = "E:\\ideaworkspace\\yanerHouse\\src\\main\\resources\\static\\images\\";//文件路径
        File file = new File(filePathPre+house_image);
        file.delete();*/
        //删除完成,删除完成后在修改
        //对修改的数据先进行处理
        HouseTools(house);
        int i =houseMapper.AgainIssue(house);
        if (i>0){
            return "againIssueInfo001";//代表重新发布成功
        }else {
            return "againIssueInfo002";//代表重新发布失败
        }
    }
    //查询所有通过核审的房源
    @Override
    public List<House> getHouses() {
        //先从数据库查询信息
        List<House> list = houseMapper.getHouses();
        for (int i = 0; i<list.size();i++){
            //处理数据渲染视图
            HouseDateToPageTools(list.get(i));
            HouseStateTool(list.get(i));
        }
        //返回数据
        return list;
    }
    //修改房源（修改上线的房源）
    @Override
    public String updateHouse(House house) {
        HouseTools(house);
        int updateHouseInfo = houseMapper.updateHouse(house);
        if (updateHouseInfo > 0){
            return "updateHouseInfo001";//代表修改成功
        }else {
            return "updateHouseInfo001";//代表修改失败
        }
    }
    //下架房源(只能下架已经上线的房源)
    @Override
    public String deleteHouse(House house) {
        int deleteinfo = houseMapper.deleteHouse(house);
        if (deleteinfo > 0){
            return "deleteHouse001";//下架成功
        }else{
            return "deleteHouse002";//下架失败
        }
    }

    @Override
    public List<House> WXGetHouses() {
        return houseMapper.wxgetHouses();

    }
    //微信搜索房源模糊查询
    @Override
    public List<House> WXGetHousesBySearch(String queryName) {
        return houseMapper.WXGetHousesBySearch(queryName);
    }

    //处理网页传来的房源信息存入数据库
    public void HouseTools(House house) {
        //处理出租方式
        if ("sentType1".equals(house.getHouse_sentType())) {
            house.setHouse_sentType("合租");
        } else if ("sentType2".equals(house.getHouse_sentType())){
            house.setHouse_sentType("整租");
        }
        //处理房屋户型
        if ("shape1".equals(house.getHouse_shape())) {
            house.setHouse_shape("一室一厅");
        } else if ("shape2".equals(house.getHouse_shape())) {
            house.setHouse_shape("两室一厅");
        } else if ("shape3".equals(house.getHouse_shape())) {
            house.setHouse_shape("三室一厅");
        } else if ("shape4".equals(house.getHouse_shape())) {
            house.setHouse_shape("三室两厅");
        } else if ("shape5".equals(house.getHouse_shape())) {
            house.setHouse_shape("四室一厅");
        }else if ("shape6".equals(house.getHouse_shape())) {
            house.setHouse_shape("四室两厅");
        }

        //处理押金形式
        if ("moneyType1".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("押一付一");
        }else if ("moneyType2".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("押一付二");
        }else if ("moneyType3".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("押一付三");
        }else if ("moneyType4".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("无押金");
        }

        //处理房源标签
        String getHouse_flag = house.getHouse_flag();
        String chuliHouse_flag = "";
        String [] house_flag = getHouse_flag.split(",");
        for (int j = 0;j<house_flag.length;j++){
            if ("houseFlag1".equals(house_flag[j])){
                house_flag[j] = "首次出租";
            }else if ("houseFlag2".equals(house_flag[j])){
                house_flag[j] = "临近地铁";
            }else if ("houseFlag3".equals(house_flag[j])){
                house_flag[j] = "小区优美";
            }else if ("houseFlag4".equals(house_flag[j])){
                house_flag[j] = "交通便捷";
            }else if ("houseFlag5".equals(house_flag[j])){
                house_flag[j] = "学区房";
            }else if ("houseFlag6".equals(house_flag[j])){
                house_flag[j] = "精装修";
            }else if ("houseFlag7".equals(house_flag[j])){
                house_flag[j] = "无押金";
            }else if ("houseFlag8".equals(house_flag[j])){
                house_flag[j] = "深呼吸";
            }else if ("houseFlag9".equals(house_flag[j])){
                house_flag[j] = "短租";
            }
            chuliHouse_flag = chuliHouse_flag+house_flag[j]+",";
        }
        chuliHouse_flag = chuliHouse_flag.substring(0,chuliHouse_flag.length()-1);
        house.setHouse_flag(chuliHouse_flag);
    }
    //处理传来的图片
    public String houseImage(String imagePath){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileOutputStream fo2 = null;
        FileChannel in = null;
        FileChannel out = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String preFilePath01 = "D:\\掩耳租房图片";
        String preFilePath02 = "E:\\ideaworkspace\\yanerHouse\\src\\main\\resources\\static\\images";//文件路径
        String preFilepath03 =  "D:\\wxxcxworkspace\\yEwxSmall\\images\\houseImages";//小程序图片路径
        String fileName = imagePath.substring(imagePath.lastIndexOf("\\")+1);//获取文件名
        File fileDu = new File(preFilePath01+"\\"+fileName);
        File fileXie = new File(preFilePath02+"\\"+date+fileName);
        File fileXieToWx = new File(preFilepath03+"\\"+date+fileName);
        System.out.println(fileXie);
        try {
            fi = new FileInputStream(fileDu);
            fo = new FileOutputStream(fileXie);
            fo2 = new FileOutputStream(fileXieToWx);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道,idea写完
            out = fo2.getChannel();
            in.transferTo(0,in.size(),out);//小程序写完
            fi.close();
            in.close();
            fo.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return date+fileName;
    }
    //处理数据库取出的信息渲染视图
    public void HouseDateToPageTools(House house) {
        //处理出租方式
       if ("合租".equals(house.getHouse_sentType())) {
            house.setHouse_sentType("sentType1");
        }else if ("整租".equals(house.getHouse_sentType())) {
            house.setHouse_sentType("sentType2");
        }
        //处理房屋户型
       if ("一室一厅".equals(house.getHouse_shape())) {
            house.setHouse_shape("shape1");
        }else if ("两室一厅".equals(house.getHouse_shape())) {
            house.setHouse_shape("shape2");
        }else if ("三室一厅".equals(house.getHouse_shape())) {
            house.setHouse_shape("shape3");
        }else if ("三室两厅".equals(house.getHouse_shape())) {
            house.setHouse_shape("shape4");
        }else if ("四室一厅".equals(house.getHouse_shape())) {
            house.setHouse_shape("shape5");
        }else if ("四室两厅".equals(house.getHouse_shape())) {
            house.setHouse_shape("shape6");
        }
       //处理押金形式
       if ("押一付一".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("moneyType1");
        }else if ("押一付二".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("moneyType2");
        }else if ("押一付三".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("moneyType3");
        }else if ("无押金".equals(house.getHouse_moneyType())){
            house.setHouse_moneyType("moneyType4");
        }

        //处理房源标签
        String getHouse_flag = house.getHouse_flag();
        String chuliHouse_flag = "";
        String [] house_flag = getHouse_flag.split(",");
        for (int j = 0;j<house_flag.length;j++){
            if ("首次出租".equals(house_flag[j])){
                house_flag[j] = "houseFlag1";
            }else if ("临近地铁".equals(house_flag[j])){
                house_flag[j] = "houseFlag2";
            }else if ("小区优美".equals(house_flag[j])){
                house_flag[j] = "houseFlag3";
            }else if ("交通便捷".equals(house_flag[j])){
                house_flag[j] = "houseFlag4";
            }else if ("精装修".equals(house_flag[j])){
                house_flag[j] = "houseFlag6";
            }else if ("无押金".equals(house_flag[j])){
                house_flag[j] = "houseFlag7";
            }else if ("深呼吸".equals(house_flag[j])){
                house_flag[j] = "houseFlag8";
            }else if ("短租".equals(house_flag[j])){
                house_flag[j] = "houseFlag9";
            }
            chuliHouse_flag = chuliHouse_flag+house_flag[j]+",";
        }
        chuliHouse_flag = chuliHouse_flag.substring(0,chuliHouse_flag.length()-1);
        house.setHouse_flag(chuliHouse_flag);
    }
    public void HouseStateTool(House house){
        //处理房屋状态
        if("1".equals(house.getHouse_state())){
            house.setHouse_state("正常");
        }else  if("2".equals(house.getHouse_state())){
            house.setHouse_state("被租用");
        }else  if("-1".equals(house.getHouse_state())){
            house.setHouse_state("禁用");
        }
        //处理审批信息
        if ("1".equals(house.getIsApproval())){
            house.setIsApproval("通过");
        }else if ("0".equals(house.getIsApproval())){
            house.setIsApproval("等待中");
        }else if ("-1".equals(house.getIsApproval())){
            house.setIsApproval("拒绝");
        }
    }
}
