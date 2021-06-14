package com.learningjava.expensetrackerapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Category_TBL")
@Entity(name = "Category_TBL")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "categoryId", nullable = false, updatable = false)
    private Integer categoryId;

    @Column(name = "userId")
    private Integer userId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "totalExpense")
    private Double totalExpense;
}
