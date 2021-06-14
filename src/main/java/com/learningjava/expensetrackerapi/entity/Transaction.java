package com.learningjava.expensetrackerapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer transactionId;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer userId;
    private String note;
    @NotNull
    private Double amount;
    @NotNull
    private long transactionDate;
}
