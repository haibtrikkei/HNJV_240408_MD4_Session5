package com.example.jv240408_security_api.security.principal;

import com.example.jv240408_security_api.model.entity.Role;
import com.example.jv240408_security_api.model.entity.Users;
import com.example.jv240408_security_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findUsersByUsername(username).orElseThrow(() -> new NoSuchElementException("Khong ton tai username: " + username));
        return CustomUserDetail.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .fullName(users.getFullName())
                .email(users.getEmail())
                .phone(users.getPhone())
                .status(users.getStatus())
                .authorities(mapToAuthorties(users.getRoles()))
                .build();
    }

    private Collection<SimpleGrantedAuthority> mapToAuthorties(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().toString())).toList();
    }
}
