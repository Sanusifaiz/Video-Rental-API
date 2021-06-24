package com.learningjava.VideoRentalApi.repositories;


import com.learningjava.VideoRentalApi.Enums.videoGenreEnum;
import com.learningjava.VideoRentalApi.Enums.videoTypeEnum;
import com.learningjava.VideoRentalApi.entity.Video;

import com.learningjava.VideoRentalApi.exceptions.EtResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>  {

    Video findOneByvideoTitle(String videoTitle) throws EtResourceNotFoundException;

    Video findByvideoId(String videoId) throws EtResourceNotFoundException;

    Page<Video> findByvideoType(videoTypeEnum videoType, Pageable pageable) throws EtResourceNotFoundException;

    Page<Video> findByvideoGenre(videoGenreEnum videoGenre, Pageable pageable) throws EtResourceNotFoundException;

    String deleteByvideoId(String videoId);



}
