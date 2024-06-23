package com.example.ecomver_web.repository;
import com.example.ecomver_web.model.entity.User;
import com.example.ecomver_web.model.response.UserResponse;
import org.apache.ibatis.annotations.*;
@Mapper
public interface UserRepository{

    @Select("""
    SELECT * FROM tbuser WHERE tbuser.username = #{username}
    """)
    User findUserByUsername(String username);
    @Insert("""
        INSERT INTO tbUser(username,phoneNumber,password)
        VALUES (#{user.username}, #{user.phoneNumber}, #{user.password})
    """)
    void createNewUser(@Param("user") User user);

    @Select("""
    SELECT * FROM tbuser WHERE userId = #{id}
    """)
    UserResponse findUserById(@Param("id") int id);
}

