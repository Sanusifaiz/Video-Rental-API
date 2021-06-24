package com.learningjava.VideoRentalApi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.GUIDGenerator;

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
    private String PhoneNumber;
    private String Role;
    private String email;
    private String password;
}
