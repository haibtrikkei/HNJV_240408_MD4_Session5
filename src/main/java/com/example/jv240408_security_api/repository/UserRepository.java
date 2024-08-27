package com.example.jv240408_security_api.repository;

import com.example.jv240408_security_api.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findUsersByUsername(String username);
}
