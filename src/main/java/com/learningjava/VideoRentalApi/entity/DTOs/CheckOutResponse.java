package com.learningjava.VideoRentalApi.entity.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutResponse {
    private String userName;
    private String totalPrice;
}
