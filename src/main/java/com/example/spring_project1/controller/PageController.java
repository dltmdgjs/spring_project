package com.example.spring_project1.controller;


import com.example.spring_project1.entity.Menu;
import com.example.spring_project1.service.MenuRestService;
import com.example.spring_project1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    private MenuRestService menuRestService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) { // 널 값에 대한 참조 시 nullPointerException 에러 발생.
            String writer = userService.findWriter(authentication.getName());
            model.addAttribute("writer", writer);
        }
        return "index";
    }

    @GetMapping("/registerPage")
    public String registerPage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf",csrfToken);
        return "register/index";
    }

    @GetMapping("/loginPage")
    public String loginPage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf",csrfToken);
        return "login/index";
    }

    @GetMapping("/noticeAddPage")
    public String noticeAddPage(Model model, Authentication authentication) {
        String writer = userService.findWriter(authentication.getName()); //authentication.getName() == username(ID)
        model.addAttribute("writer", writer);
        return "noticeAdd/index";
    }

    @GetMapping("/noticeCheckPage")
    public String showNoticeCheckPage(@RequestParam("idx") int idx, Model model, Authentication authentication) {
        if (authentication != null) { // 널 값에 대한 참조 시 nullPointerException 에러 발생.
            String writer = userService.findWriter(authentication.getName());
            model.addAttribute("writer", writer);
        }
        Menu menu = menuRestService.boardContent(idx);
        model.addAttribute("menu",menu);
        return "noticeCheck/index";
    }

    @GetMapping("/noticeModifyPage")
    public String showNoticeModifyPage(@RequestParam("idx") int idx, Model model, Authentication authentication) {
        if (authentication != null) { // 널 값에 대한 참조 시 nullPointerException 에러 발생.
            String writer = userService.findWriter(authentication.getName());
            model.addAttribute("writer", writer);
        }
        Menu menu = menuRestService.boardContent(idx);
        model.addAttribute("menu",menu);
        return "noticeModify/index";
    }


}

