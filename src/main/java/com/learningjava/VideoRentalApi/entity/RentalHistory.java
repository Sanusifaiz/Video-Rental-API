package com.learningjava.VideoRentalApi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RENTAL_HISTORY_TBL")
public class RentalHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id", nullable = false, updatable = false)
    private Integer Id;
    private String RentalID;
    private String userName;
    private String videoId;
    private Integer rentalDays;
    private Double totalPrice;
    private Long transactionDate;
}
