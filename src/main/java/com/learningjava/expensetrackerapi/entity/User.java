package com.learningjava.expensetrackerapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
