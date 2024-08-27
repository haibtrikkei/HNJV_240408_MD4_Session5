package com.example.jv240408_security_api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegister {
    @NotBlank(message = "Username is empty!")
    private String username;
    @NotBlank(message = "Password is empty!")
    private String password;
    @NotBlank(message = "Full name is empty!")
    private String fullName;
    @NotBlank(message = "Email is empty!")
    private String email;
    @NotBlank(message = "Phone is empty!")
    private String phone;
    @NotNull(message = "Role is empty!")
    private Set<String> roles;
}
