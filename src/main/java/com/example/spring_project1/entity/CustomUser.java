package com.example.spring_project1.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
    private com.example.spring_project1.entity.User user;
    public CustomUser(com.example.spring_project1.entity.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }
}
