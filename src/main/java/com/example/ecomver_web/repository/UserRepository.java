package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRepository{

    @Select("""
    SELECT * FROM tbuser WHERE tbuser.username = #{username}
    """)
    @Results(id = "userMapping",value = {
    })
    User findUserByUsername(String username);
    @Insert("""
    INSERT INTO tbUser(username,phoneNumber,password)
    VALUES (#{user.username}, #{user.phoneNumber}, #{user.password})
    """)
    void createNewUser(@Param("user") User user);
}

