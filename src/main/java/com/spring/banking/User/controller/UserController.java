package com.spring.banking.User.controller;

import com.spring.banking.User.dto.UserDTO;
import com.spring.banking.User.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/members")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/signUp")
    public UserDTO signUp(@ModelAttribute UserDTO user){

        UserDTO signUpUser = userService.signUp(user);

        return signUpUser;
    }

    //로그인
    @PostMapping("/singIn")
    public UserDTO singIn(UserDTO user, HttpSession session){

        UserDTO loginUser = userService.signIn(user);

        session.setAttribute("userId", loginUser.getUserId());
        session.setAttribute("name", loginUser.getName());


        return loginUser;
    }



}
