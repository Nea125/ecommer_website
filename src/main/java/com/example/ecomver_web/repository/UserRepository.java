package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.entity.User;
import com.example.ecomver_web.model.response.UserResponse;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRepository {

    @Select("""
    SELECT * FROM tbuser WHERE tbuser.username = #{email}
    """)
    @Results(id = "userMapping", value = {
            @Result(property = "userId", column = "userid"),
            @Result(property = "email", column = "username"),
            @Result(property = "phoneNumber", column = "phonenumber")
    })
    User findUserByUsername(String username);

    @Insert("""
        INSERT INTO tbUser(username, phoneNumber, password)
        VALUES (#{user.email}, #{user.phoneNumber}, #{user.password})
    """)
    void createNewUser(@Param("user") User user);

    @Select("""
    SELECT * FROM tbuser WHERE userId = #{id}
    """)
    @ResultMap("userMapping")
    UserResponse findUserById(@Param("id") int id);
}
