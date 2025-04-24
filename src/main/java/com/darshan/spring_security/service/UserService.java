package com.darshan.spring_security.service;

import com.darshan.spring_security.dao.UserRepo;
import com.darshan.spring_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public User addUser(User user) {
       user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
       System.out.println(user.getUserPassword());
       return repo.save(user);
    }
}
