package com.spring.banking.User.service;

import com.spring.banking.User.dto.UserDTO;
import com.spring.banking.User.entity.User;
import com.spring.banking.User.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository repository;

    @Test
    void signUp() {
        //given
        UserDTO user = userService.signUp(UserDTO.builder()
                .userId("sori")
                .password("qlalfqjsgh")
                .name("임소리")
                .build());


        //when
        User signUpUser = repository.findById("sori")
                .orElseThrow(() ->{throw new IllegalStateException("아이디 또는 비밀번호가 일치하지 않습니다");});

        //then
        assertThat(signUpUser.getUserId()).isEqualTo(user.getUserId());

    }

    @Test
    void duplicateSignUp() {

        try {
            //given
            UserDTO user = userService.signUp(UserDTO.builder()
                    .userId("sori")
                    .password("qlalfqjsgh")
                    .name("임소리")
                    .build());

            userService.signUp(UserDTO.builder()
                    .userId("sori")
                    .password("qlalfqjsgh")
                    .name("임소리")
                    .build());

            //when
        }catch (IllegalStateException e){

            //then
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }




    }
    @Test
    void signIn() {
        //given
        UserDTO user = userService.signUp(UserDTO.builder()
                .userId("sori")
                .password("qlalfqjsgh")
                .name("임소리")
                .build());

        UserDTO signInUser = userService.signIn(user);

        //when
        User user1 = repository.findById("sori")
                .orElseThrow(() ->{throw new IllegalStateException("존재하지 않는 아이디입니다.");});

        //then
        assertThat(user1.getUserId()).isEqualTo(signInUser.getUserId());
    }
}