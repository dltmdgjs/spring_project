package com.example.spring_project1.controller;

import com.example.spring_project1.entity.Role;
import com.example.spring_project1.entity.User;
import com.example.spring_project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        String userPassword = user.getPassword();
        user.setRole(Role.MEMBER);
        String passwordEncoded = passwordEncoder.encode(userPassword); //암호화
        user.setPassword(passwordEncoded);
        userService.insertUser(user);
        return "redirect:/loginPage";
    }

}
