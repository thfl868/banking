package com.spring.banking.User.entity;

import com.spring.banking.User.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String password;

    private String name;

    @Builder
    public User(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public static User toUser(UserDTO user){
        return User.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName()).build();
    }
}
