package com.example.spring_project1.controller;

import com.example.spring_project1.entity.Menu;
import com.example.spring_project1.service.MenuRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// RestApi 개발 시 사용.
// RestController는 JSON 타입의 데이터를 벡엔드에서 프론트로 전달할 때 사용.(= @Controller + @ResponseBody)
// (이전의 Controller는 http기반. 주로 view(페이지)를 반환하기 위해 사용.)
// (물론 @Controller에서 @ResponseBody 어노테이션을 사용해 데이터를 전달할 수 있지만
// 데이터를 반환하는 @RestController를 사용함으로써 view를 반환하는 @Controller와 분리 해주는게 좋다.)
@RestController
public class MenuRestController {

    @Autowired
    private MenuRestService menuRestService;

    // REST API 구성
    //      > Http method(get post update delete)
    //      > URI
    //      > Description(data type)

    // 메뉴(모든게시판) 조회 : 모든 게시판을 가져옴
    // ResponseEntity : http 상태코드와 데이터를 반환.(200(성공), 404, 500 ...)
    // 객체를 ResponseEntity로 감싸 반환.
    @GetMapping("/menu/all")
    public ResponseEntity<List<Menu>>getAllMenus() {
        List<Menu> menus = menuRestService.getLists();
        // 게시글(객체)이 존재하는 경우
        if(menus != null && !menus.isEmpty()) {
            return ResponseEntity.ok(menus); // 200번(성공)과 데이터(JSON형식)를 위 경로로 넘김
        } else { // 게시글(객체)이 존재하지 않는 경우
            return ResponseEntity.noContent().build(); // 204번(데이터 존재 X), 빈 데이터 공간을 넘김.
        }
    }

    // 메뉴(한개의 게시판 생성) 생성
    @PostMapping("/menu/add")
    public ResponseEntity<String> addMenu(@RequestBody Menu menu) { // @RequestBody : 프론트에서 벡엔드로 데이터 넘겨받음.
        // 작성된 시점의 날짜를 자동으로 설정
        if(menu.getIndate()==null || menu.getIndate().isEmpty()) {
            menu.setIndate((LocalDate.now().toString()));
        }
        // 조회수는 초기값 0으로 설정
        menu.setCount(0);
        // 메뉴를 데이터베이스에 삽입
        menuRestService.boardInsert(menu);
        return ResponseEntity.ok("게시글이 작성되었음");
    }

    // 메뉴(한개의 게시판 수정) 수정
    @PutMapping("/menu/update/{idx}")
    public void updateMenu(@RequestBody Menu menu, @PathVariable("idx") int idx) { // @PathVariable : 경로에 변수값을 붙일 수 있음.
        menu.setIdx(idx); //특정 idx를 가진 게시글을 menu안의 title과 content를 가져와서 수정.
        menuRestService.boardUpdate(menu);
    }

    //메뉴(한개의 게시판 삭제) 삭제
    @DeleteMapping("/menu/delete/{idx}")
    public void deleteMenu(@PathVariable("idx") int idx) {
        menuRestService.boardDelete(idx);
    }

    //특정메뉴(한개의 게시판 내용 조회) 조회
    @GetMapping("/menu/{idx}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("idx") int idx) {
        Menu menu = menuRestService.boardContent(idx);
        if(menu != null) {
            return ResponseEntity.ok(menu); //200번과 menu 객체를 벡에서 프론트로 넘김.
        }
        else {
            return ResponseEntity.notFound().build(); //404번(not found)을 넘김.
        }
    }

    //특정 메뉴의(게시판 조회수 증가) 조회수 증가
    @PutMapping("menu/count/{idx}")
    public void incrementMenuCount(@PathVariable("idx") int idx) {
        menuRestService.boardCount(idx);
    }

}


// swagger란 : Api 개발 시 사용하는 도구.
