package com.spring.banking.User.dto;

import com.spring.banking.User.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String userId;

    private String password;

    private String name;

    @Builder
    public UserDTO(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public static UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName()).build();
    }
}
