package com.example.spring_project1.service;

import com.example.spring_project1.entity.CustomUser;
import com.example.spring_project1.entity.User;
import com.example.spring_project1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServicelmpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.findByUsername(username);
        if(user == null) {
            // 로그인 시도 시 데이터가 없는 경우
            throw new UsernameNotFoundException(username+"존재하지 않습니다.");
        }
        // 로그인 시도 시 DB에 유저 정보가 존재하는 경우
        return new CustomUser(user);
    }
}
