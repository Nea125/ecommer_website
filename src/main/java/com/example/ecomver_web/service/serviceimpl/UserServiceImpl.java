package com.example.ecomver_web.service.serviceimpl;

import com.example.ecomver_web.model.CustomUserDetail;
import com.example.ecomver_web.model.entity.User;
import com.example.ecomver_web.model.request.UserRegisterRequest;
import com.example.ecomver_web.model.response.UserRegisterResponse;
import com.example.ecomver_web.repository.UserRepository;
import com.example.ecomver_web.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetail(user);
    }

    @Override
    public UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest) {
        User user = mapper.map(userRegisterRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        userRepository.createNewUser(user);
        return mapper.map(user, UserRegisterResponse.class);
    }
}
