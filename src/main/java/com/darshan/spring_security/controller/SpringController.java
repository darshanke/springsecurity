package com.darshan.spring_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {


    @GetMapping("csrf-token")
    public CsrfToken getToken(HttpServletRequest request){
        System.out.println("hitting the token");

        CsrfToken token =  (CsrfToken) request.getAttribute("_csrf");

        System.out.println(token);
        return token;
    }

    @GetMapping("hello")
    public String firstController(HttpServletRequest request){
        return "hello world "+request.getSession().getId();
    }

    @GetMapping("about")
    public String firstControllers(HttpServletRequest request){
        return "hello world "+request.getSession().getId();
    }



}
