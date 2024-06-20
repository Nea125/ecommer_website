package com.example.ecomver_web.controller;

import com.example.ecomver_web.model.request.UserLoginRequest;
import com.example.ecomver_web.model.request.UserRegisterRequest;
import com.example.ecomver_web.model.response.UserLoginTokenResponse;
import com.example.ecomver_web.model.response.UserRegisterResponse;
import com.example.ecomver_web.security.JwtService;
import com.example.ecomver_web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        UserRegisterResponse authRegister = userService.createNewUser(userRegisterRequest);
        return new ResponseEntity<>(authRegister, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest userLoginRequest) throws Exception {
        authenticate(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(userLoginRequest.getUsername());
        final String token = jwtService.generateToken(userDetails);
        UserLoginTokenResponse authResponse = new UserLoginTokenResponse("Login successfully",HttpStatus.OK,token,200, LocalDateTime.now());
        return ResponseEntity.ok(authResponse);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            UserDetails user = userService.loadUserByUsername(email);
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new Exception("Your password is incorrect please try again");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
