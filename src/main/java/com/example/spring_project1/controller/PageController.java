package com.example.spring_project1.controller;


import com.example.spring_project1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
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
    public String noticeAddPage() {
        return "noticeAdd/index";
    }


}

