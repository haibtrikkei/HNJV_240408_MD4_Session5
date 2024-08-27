package com.example.jv240408_security_api.model.dto.response;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JWTResponse {
    private String username;
    private String fullName;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean enable;
    private String jwtToken;
}
