package com.darshan.spring_security.controller;

import com.darshan.spring_security.model.User;
import com.darshan.spring_security.service.JwtTokenService;
import com.darshan.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("user")
    public String addUser(@RequestBody User user){
       service.addUser(user);

      return "SUCCESS";
    }

    @PostMapping("login")
    public String login(@RequestBody     User user){

        System.out.println(user);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (user.getUserName(), user.getUserPassword()));


        if(authentication.isAuthenticated()){
            return jwtTokenService.getJwToken(user.getUserName());
        }else{
            return "User Dosn't exist";
        }

    }
}
