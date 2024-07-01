package com.example.ecomver_web.controller;

import com.example.ecomver_web.model.request.UserLoginRequest;
import com.example.ecomver_web.model.request.UserRegisterRequest;
import com.example.ecomver_web.model.response.APIResponse;
import com.example.ecomver_web.model.response.UserLoginTokenResponse;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        userService.createNewUser(userRegisterRequest);
        return new ResponseEntity<>(new APIResponse<>("Register successfully", HttpStatus.CREATED, null, 201, LocalDateTime.now()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        try {
            authenticate(userLoginRequest.getEmail(), userLoginRequest.getPassword());
            final UserDetails userDetails = userService.loadUserByUsername(userLoginRequest.getEmail());
            final String token = jwtService.generateToken(userDetails);
            UserLoginTokenResponse authResponse = new UserLoginTokenResponse("Login successfully", HttpStatus.OK, 200, token, LocalDateTime.now());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(new APIResponse<>(e.getMessage(), HttpStatus.UNAUTHORIZED, null, 401, LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
        }
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            authenticationManager.authenticate(authenticationToken);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
