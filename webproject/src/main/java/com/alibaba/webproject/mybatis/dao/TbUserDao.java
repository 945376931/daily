package com.alibaba.webproject.mybatis.dao;

import com.alibaba.webproject.mybatis.dto.UserDto;
import com.alibaba.webproject.mybatis.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TbUserDao {
    @Results({
//            @Result(property = "id",column = "id"),
            @Result(property = "nickname",column ="nickname"),
            @Result(property = "tel",column = "tel")
    })
    @Select("SELECT * FROM user_table WHERE tel = #{tel}")
    User getUserByName(String tel);

    /**注册新用户 **/
    @Insert("INSERT into user_table(tel,passwd) VALUES (#{tel},#{passwd})")
    void saveRegister( User user);

    /**修改用户 **/
    @Update("UPDATE user_table set 'nickname'=#{nickname},'signature'=#{signature},'sex'=#{sex} ,'tel'=#{tel},'mail'=#{mail},'password'=#{passwd}")
    void updateUserDetail(@Param("user") User user);

    /**查询登录的用户信息**/
    @Select("SELECT * FROM user_table where id=#{id}")
    User findLoginUserById(UserDto dto);





}
