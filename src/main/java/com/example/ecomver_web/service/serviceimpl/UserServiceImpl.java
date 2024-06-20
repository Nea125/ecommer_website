package com.example.ecomver_web.service.serviceimpl;

import com.example.ecomver_web.model.CustomUserDetail;
import com.example.ecomver_web.model.User;
import com.example.ecomver_web.repository.UserRepository;
import com.example.ecomver_web.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        return new CustomUserDetail(user);
    }
}
