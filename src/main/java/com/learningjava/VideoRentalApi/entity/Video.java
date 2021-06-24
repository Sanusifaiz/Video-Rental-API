package com.learningjava.VideoRentalApi.entity;


import com.learningjava.VideoRentalApi.Enums.videoGenreEnum;
import com.learningjava.VideoRentalApi.Enums.videoTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import java.text.DecimalFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VIDEOS_TBL")
public class Video {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id", nullable = false, updatable = false)
    private Integer Id;

    @Column(name = "videoId")
    private String videoId;

    private String videoTitle;
    @Enumerated(EnumType.STRING)
    private videoTypeEnum videoType;
    @Enumerated(EnumType.STRING)
    private videoGenreEnum videoGenre;
    private Integer maxAge;
    private Integer yearReleased;
    private Double Price;

}
