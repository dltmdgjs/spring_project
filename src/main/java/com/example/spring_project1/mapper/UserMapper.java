package com.example.spring_project1.mapper;

import com.example.spring_project1.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
//java와 mysql 언어를 통역하는 역할을 함.(매핑)
public interface UserMapper {

    // CRUD 기능.
    @Insert("INSERT INTO backend_spring_project.user(username,password,writer,role) " +
            "VALUES(#{username},#{password},#{writer},#{role})")
    void insertUser(User user);

    @Select("SELECT username,password,writer,role FROM backend_spring_project.user WHERE username=#{username}")
    User findByUsername(String username);

    @Select("SELECT writer FROM backend_spring_project.user WHERE username=#{username}")
    String findWriter(String username);

    //@Delete()

    //@Update()
}
