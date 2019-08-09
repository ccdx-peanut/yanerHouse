package com.yaner.house.mapper;

import com.yaner.house.bean.Steward;
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
public interface StewardMapper {
    //添加管家
    @Insert("insert into steward (ste_name,ste_six,ste_IdCard,ste_phone) values (#{ste_name},#{ste_six},#{ste_IdCard},#{ste_phone})")
    void addSteward(Steward steward);

    //修改管家
    @Update("update steward set ste_name=#{ste_name},ste_six=#{ste_six},ste_IdCard=#{ste_IdCard}," +
            "ste_phone=#{ste_phone},ste_state=#{ste_state} where ste_id=#{ste_id}")
    void updateSteward(Steward steward);

    //删除管家,假删除，把管家的ste_state改为-1
    @Update("update steward set ste_state= -1 where ste_id=#{ste_id}")
    void deleteSteward(Steward steward);

    //查询所有管家信息
    @Select("select * from steward")
    List<Steward> getAllStewards();
}
