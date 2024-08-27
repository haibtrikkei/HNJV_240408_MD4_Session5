package com.example.jv240408_security_api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "username",length = 200,nullable = false,unique = true)
    private String username;
    @Column(name = "password",length = 200,nullable = false)
    private String password;
    @Column(name = "full_name",length = 70)
    private String fullName;
    @Column(name = "email",length = 100,unique = true)
    private String email;
    @Column(name = "phone",length = 15,unique = true)
    private String phone;
    @Column(name = "status")
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
