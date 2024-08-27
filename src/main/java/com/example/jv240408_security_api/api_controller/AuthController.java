package com.example.jv240408_security_api.api_controller;

import com.example.jv240408_security_api.model.dto.request.LoginRequest;
import com.example.jv240408_security_api.model.dto.request.UserRegister;
import com.example.jv240408_security_api.model.dto.response.DataResponse;
import com.example.jv240408_security_api.model.dto.response.JWTResponse;
import com.example.jv240408_security_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserRegister userRegister){
        userService.save(userRegister);
        return new ResponseEntity<>(new DataResponse<String>("Successfully!", HttpStatus.CREATED),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse<JWTResponse>> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(new DataResponse<>(userService.login(loginRequest),HttpStatus.OK),HttpStatus.OK);
    }
}
