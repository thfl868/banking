package com.spring.banking.User.service;

import com.spring.banking.User.dto.UserDTO;
import com.spring.banking.User.entity.User;
import com.spring.banking.User.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     * @param user
     * @return user
     */
    public UserDTO signUp(UserDTO user) {

        //중복확인
        validateUserInfo(user);
        //회원가입
        return UserDTO.toUserDTO(userRepository.save(User.toUser(user)));
    }

    /**
     * 로그인
     * @param user
     * @return loginUser{userId, name}
     */
    public UserDTO signIn(UserDTO user) {

        //로그인
       User loginUser = userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())
			.orElseThrow(() ->{throw new IllegalStateException("아이디 또는 비밀번호가 일치하지 않습니다");});

        return UserDTO.toUserDTO(loginUser);
    }


    /**
     * 아이디로 중복확인
     * @param user
     */
    private void validateUserInfo(UserDTO user) {

        log.debug("중복확인");
        userRepository.findById(user.getUserId()).ifPresent(
                u-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


}
