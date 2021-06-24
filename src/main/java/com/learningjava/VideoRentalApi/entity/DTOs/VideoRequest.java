package com.learningjava.VideoRentalApi.entity.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequest {
    private String userName;
    private String videoTitle;
    private Integer numberOfDays;
}
