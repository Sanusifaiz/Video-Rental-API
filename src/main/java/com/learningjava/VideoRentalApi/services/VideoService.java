package com.learningjava.VideoRentalApi.services;

import com.learningjava.VideoRentalApi.entity.DTOs.CheckOutResponse;
import com.learningjava.VideoRentalApi.entity.DTOs.VideoRequest;
import com.learningjava.VideoRentalApi.entity.Video;
import com.learningjava.VideoRentalApi.exceptions.EtBadRequestException;
import com.learningjava.VideoRentalApi.exceptions.EtResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface VideoService {
    Page<Video> fetchAllVideos(Pageable page) throws
            EtBadRequestException;

    Video fetchVideoByTitle(String VideoTitle) throws
            EtResourceNotFoundException;

    Page<Video> fetchVideoByType(String VideoType, Pageable page) throws
            EtResourceNotFoundException;

    Page<Video> fetchVideoByGenre(String videoGenre, Pageable page) throws
            EtResourceNotFoundException;

    String addVideo(Video video) throws
            EtBadRequestException;

    CheckOutResponse CalculatePrice(List<VideoRequest> videoRequest) throws EtBadRequestException;

    Video updateVideo(Video video) throws
            EtBadRequestException;

    String removeVideo(String VideoId) throws
            EtResourceNotFoundException;


}