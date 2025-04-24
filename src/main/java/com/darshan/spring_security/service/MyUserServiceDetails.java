package com.darshan.spring_security.service;

import com.darshan.spring_security.dao.UserRepo;
import com.darshan.spring_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserServiceDetails implements UserDetailsService {


//    @Autowired
   private final UserRepo repo;

    public MyUserServiceDetails(UserRepo repo) {
        this.repo = repo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUserName(username);
        System.out.println(user);
        if(user == null){
            System.out.println("user not found 404");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }
}
