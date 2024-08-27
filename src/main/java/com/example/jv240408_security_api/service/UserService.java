package com.example.jv240408_security_api.service;

import com.example.jv240408_security_api.model.dto.request.LoginRequest;
import com.example.jv240408_security_api.model.dto.request.UserRegister;
import com.example.jv240408_security_api.model.dto.response.JWTResponse;
import com.example.jv240408_security_api.model.entity.Users;

public interface UserService {
    Users save(UserRegister userRegister);
    public JWTResponse login(LoginRequest loginRequest);
}
