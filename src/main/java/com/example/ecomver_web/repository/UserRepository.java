package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository{

    @Select("""
    SELECT * FROM tbuser WHERE tbuser.username = #{username}
    """)
    @Results(id = "userMapping",value = {
    })
    User findUserByEmail(String username);
}

