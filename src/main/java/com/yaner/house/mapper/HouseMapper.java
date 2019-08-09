package com.yaner.house.mapper;

import com.yaner.house.bean.House;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * @author:sfq
 * @create 2019-04-16  17:48
 **/
@Mapper
public interface HouseMapper {
    //添加房源
    @Insert("insert into house (house_sentType,house_street,house_xq,house_roof,house_shape,house_area,house_money," +
            "house_moneyType,house_title,house_flag,house_image,admin_issue) values (#{house_sentType},#{house_street}," +
            "#{house_xq},#{house_roof},#{house_shape},#{house_area},#{house_money},#{house_moneyType},#{house_title}," +
            "#{house_flag},#{house_image},#{admin_issue})")
    int addHouse(House house);

    //根据发布人的ID 查询出所有由该ID发布的信息
    @Select("select * from house where admin_issue = #{admin_issue}")
    List<House> getMyIssuesById(String admin_issue);

    //得到所有需要审批的信息与房屋状态为正常的
    @Select("select * from house where isApproval = 0 and house_state = 1")
    List<House> getApprovals();

    //审批通过功能
    @Update("update house set isApproval = 1 where house_id = #{house_id}")
    int agreeApproval(House house);

    //审批拒绝功能
    @Update("update house set isApproval =-1 where house_id = #{house_id}")
    int refuseApproval(House house);

    //重新上线查询信息
    @Select("select * from house where isApproval = -1 and house_state = 1 and admin_issue = #{admin_issue}")
    List<House> getAgainIssuesByAdminIssue(String admin_issue);

    //重新发布
    @Update("update house set house_sentType = #{house_sentType},house_street = #{house_street},house_xq = #{house_xq}," +
            "house_roof = #{house_roof},house_shape = #{house_shape},house_area = #{house_area},house_money = #{house_money}," +
            "house_moneyType = #{house_moneyType},house_title = #{house_title},house_flag = #{house_flag}," +
            "isApproval = 0 where house_state = 1 and admin_issue = #{admin_issue} and isApproval = -1 and house_id = #{house_id}")
    int AgainIssue(House house);

    //查询图片信息 修改会添加新的图片 老的要删除
    @Select("select house_image from house where house_id = #{house_id}")
    String getImageByHouseId(int house_id);

    //查询所有通过核审的房源
    @Select("select * from house where isApproval = 1 ")
    List<House> getHouses();

    //查询所有通过核审的房源
    @Select("select * from house where isApproval = 1 and house_state = 1")
    List<House> wxgetHouses();



    //修改房源（修改上线的房源）
    @Update("update house set house_sentType = #{house_sentType},house_street = #{house_street},house_xq = #{house_xq}," +
            " house_roof = #{house_roof},house_shape = #{house_shape},house_area = #{house_area},house_money = #{house_money}," +
            " house_moneyType = #{house_moneyType},house_title = #{house_title},house_flag = #{house_flag}" +
            " where house_state = 1 and isApproval = 1 and house_id = #{house_id}")
    int updateHouse(House house);

    //下架房源(只能下架已经上线的房源)
    @Update("update house set house_state = -1 where isApproval = 1 and house_id = #{house_id}")
    int deleteHouse(House house);

    //微信搜索房源模糊查询
    @Select("SELECT * FROM house WHERE house_title LIKE  CONCAT('%',#{queryName},'%') AND isApproval = 1 AND house_state=1")
    List<House> WXGetHousesBySearch(String queryName);

    //房源状态改为2，代表被租用
    @Update("update house set house_state = 2 where isApproval = 1 and house_state = 1 and house_id = #{house_id}")
    int houseState02(String house_id);

    //房源状态改为1，代表正常
    @Update("update house set house_state = 1 where isApproval = 1 and house_state = 2 and house_id = #{house_id}")
    int houseState01(String house_id);

    //查询房源租金
    @Select("select house_money from house where house_id = #{house_id}")
    String getMoneyByHouseId(String house_id);
}
