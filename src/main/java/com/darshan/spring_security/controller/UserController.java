package com.darshan.spring_security.controller;

import com.darshan.spring_security.model.User;
import com.darshan.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("user")
    public String addUser(@RequestBody User user){
       service.addUser(user);

      return "SUCCESS";
    }
}
