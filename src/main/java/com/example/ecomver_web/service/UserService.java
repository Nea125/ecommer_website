package com.example.ecomver_web.service;

import com.example.ecomver_web.model.request.UserRegisterRequest;
import com.example.ecomver_web.model.response.UserRegisterResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createNewUser(UserRegisterRequest userRegisterRequest);
}
