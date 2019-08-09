package com.yaner.house.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author:sfq
 * @create 2019-04-29  10:11
 * 心愿单mapper
 **/
@Mapper
public interface WishMapper {
    //添加心愿单（设计为心愿单最多添加10条记录）
    @Insert("insert into wish (house_id,user_id) values (#{house_id},#{user_id})")
    int addWishById(@Param("house_id")String house_id,@Param("user_id") String user_id);
    //查询添加的心愿单的记录
    @Select("select count(*) from wish where user_id = #{user_id}")
    int getWishByIdNum(@Param("user_id") String user_id);
    //删除心愿单记录（一条）
    @Delete("delete from wish where wish_id = #{wish_id}")
    int deleteWish(@Param("wish_id")String wish_id);
}
