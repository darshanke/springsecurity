package com.darshan.spring_security.dao;


import com.darshan.spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
