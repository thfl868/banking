package com.spring.banking.User.repository;

import com.spring.banking.User.dto.UserDTO;
import com.spring.banking.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserIdAndPassword(String userId, String password);
}
