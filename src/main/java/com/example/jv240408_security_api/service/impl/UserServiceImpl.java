package com.example.jv240408_security_api.service.impl;

import com.example.jv240408_security_api.model.dto.request.LoginRequest;
import com.example.jv240408_security_api.model.dto.request.UserRegister;
import com.example.jv240408_security_api.model.dto.response.JWTResponse;
import com.example.jv240408_security_api.model.entity.Role;
import com.example.jv240408_security_api.model.entity.Users;
import com.example.jv240408_security_api.repository.RoleRepository;
import com.example.jv240408_security_api.repository.UserRepository;
import com.example.jv240408_security_api.security.jwt.JWTProvider;
import com.example.jv240408_security_api.security.principal.CustomUserDetail;
import com.example.jv240408_security_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository  roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public Users save(UserRegister userRegister) {
        Users users = Users.builder()
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .fullName(userRegister.getFullName())
                .email(userRegister.getEmail())
                .phone(userRegister.getPhone())
                .roles(mapToRoles(userRegister.getRoles()))
                .status(true)
                .build();
        return userRepository.save(users);
    }

    @Override
    public JWTResponse login(LoginRequest loginRequest) {
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }catch (Exception ex){
            throw new NoSuchElementException("Sai username hoac password!");
        }
        CustomUserDetail userDetail = (CustomUserDetail) authenticate.getPrincipal();

        return JWTResponse.builder()
                .username(userDetail.getUsername())
                .fullName(userDetail.getFullName())
                .enable(userDetail.isEnabled())
                .authorities(userDetail.getAuthorities())
                .jwtToken(jwtProvider.generateJWTToken(userDetail))
                .build();
    }

    private Set<Role> mapToRoles(Set<String> roles) {
        Set<Role> list = new HashSet<>();
        if(!roles.isEmpty()) {
            roles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        list.add(roleRepository.findRoleByRoleName(role).orElseThrow(() -> new NoSuchElementException("Khong ton tai ROLE_ADMIN")));
                        break;
                    case "ROLE_USER":
                        list.add(roleRepository.findRoleByRoleName(role).orElseThrow(() -> new NoSuchElementException("Khong ton tai ROLE_USER")));
                        break;
                    case "ROLE_MODERATOR":
                        list.add(roleRepository.findRoleByRoleName(role).orElseThrow(() -> new NoSuchElementException("Khong ton tai ROLE_MODERATOR")));
                        break;
                }
            });
        }else{
            //mac dinh cho no role user
            list.add(roleRepository.findRoleByRoleName("ROLE_USER").orElseThrow(() -> new NoSuchElementException("Khong ton tai ROLE_USER")));
        }
        return list;
    }
}
