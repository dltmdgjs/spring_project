package com.example.spring_project1.mapper;

import com.example.spring_project1.entity.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MenuRestMapper {

    // CRUD
    @Select("SELECT idx, memID, title, content, writer, indate, count FROM backend_spring_project.menu ORDER BY idx DESC")
    public List<Menu>getLists();
    @Insert("INSERT INTO backend_spring_project.menu(memID, title, content, writer, indate) VALUES (#{memID},#{title},#{content},#{writer},#{indate})")
    public void boardInsert(Menu menu);
    @Select("SELECT idx, memID, title, content, writer, indate, count FROM backend_spring_project.menu WHERE idx=#{idx}")
    public Menu boardContent(int idx);
    @Delete("DELETE FROM backend_spring_project.menu WHERE idx=#{idx}")
    public void boardDelete(int idx);
    @Update("UPDATE backend_spring_project.menu SET title=#{title}, content=#{content}, writer=#{writer} WHERE idx=#{idx}")
    public void boardUpdate(Menu menu);
    @Update("UPDATE backend_spring_project.menu SET count=coutn+1 WHERE idx=#{idx}")
    public void boardCount(int idx);

}
