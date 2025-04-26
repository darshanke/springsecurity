package com.darshan.spring_security.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String userId;
    private String userName;
    private String userPassword;


}
