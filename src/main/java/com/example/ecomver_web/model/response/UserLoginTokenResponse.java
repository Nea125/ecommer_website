package com.example.ecomver_web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginTokenResponse {
    private String message;
    private HttpStatus status;
    private String token;
    private Integer code;
    private LocalDateTime time;
}
