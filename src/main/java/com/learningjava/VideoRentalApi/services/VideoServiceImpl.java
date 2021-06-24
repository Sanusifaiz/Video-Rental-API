package com.learningjava.VideoRentalApi.services;

import com.learningjava.VideoRentalApi.entity.DTOs.CheckOutResponse;
import com.learningjava.VideoRentalApi.entity.DTOs.VideoRequest;
import com.learningjava.VideoRentalApi.entity.RentalHistory;
import com.learningjava.VideoRentalApi.entity.Video;
import com.learningjava.VideoRentalApi.exceptions.EtBadRequestException;
import com.learningjava.VideoRentalApi.exceptions.EtResourceNotFoundException;
import com.learningjava.VideoRentalApi.logics.CopyProperty;
import com.learningjava.VideoRentalApi.repositories.RentalHistoryRepository;
import com.learningjava.VideoRentalApi.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.learningjava.VideoRentalApi.Enums.videoTypeEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
@Transactional       //rolls back any method when eroor occurs
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    RentalHistoryRepository rentalHistoryRepository;

    private videoTypeEnum videoTypeEnum;

    public com.learningjava.VideoRentalApi.Enums.videoTypeEnum getVideoTypeEnum(com.learningjava.VideoRentalApi.Enums.videoTypeEnum videoType) {
        return videoTypeEnum;
    }

    public void setVideoTypeEnum(com.learningjava.VideoRentalApi.Enums.videoTypeEnum videoTypeEnum) {
        this.videoTypeEnum = videoTypeEnum;
    }

    @Override
    public Page<Video> fetchAllVideos(Pageable page) throws EtBadRequestException {
       try {
           var Videos = videoRepository.findAll(page);
           return Videos;

       }catch (Exception e){
           throw new EtBadRequestException("An Error has occurred");
       }
    }

    @Override
    public Video fetchVideoByTitle(String VideoTitle) throws EtResourceNotFoundException {
        try {
            if(VideoTitle != null) {
                var Video = videoRepository.findOneByvideoTitle(VideoTitle);
                return Video;
            }
            return null;
        }catch (Exception e) {
            throw new EtBadRequestException("An Error has occurred");
        }
    }

    @Override
    public Page<Video> fetchVideoByType(String VideoType, Pageable page) throws EtResourceNotFoundException {
        try {
            if(VideoType != null){
                var Videos = videoRepository.findByvideoType(com.learningjava.VideoRentalApi.Enums.videoTypeEnum.valueOf(VideoType), page);
                if (Videos != null) {
                    return Videos;
                }
                return  null;
            }
            throw new EtBadRequestException("VideoType cant be null");
        } catch (Exception e) {
            throw new EtBadRequestException("An Error has occurred");
        }
    }

    @Override
    public Page<Video>  fetchVideoByGenre(String videoGenre, Pageable page) throws EtResourceNotFoundException {
        try {
            if(videoGenre != null){
                var Videos = videoRepository.findByvideoGenre(com.learningjava.VideoRentalApi.Enums.videoGenreEnum.valueOf(videoGenre), page);
                if (Videos != null) {
                    return Videos;
                }
                return  null;
            }
            throw new EtBadRequestException("VideoGenre cant be null");
        } catch (Exception e) {
            throw new EtBadRequestException("An Error has occurred");
        }
    }

    @Override
    public String addVideo(Video video) throws EtBadRequestException {
        try {
            var checkIfExist = videoRepository.findOneByvideoTitle(video.getVideoTitle());
            Random rand = new Random();
            var randomNum = 100000 + rand.nextInt((100000 - 500) + 1);
            if(checkIfExist == null) {
                String videoId = new StringBuilder().append(java.util.UUID.randomUUID()).append(randomNum).toString();
                video.setVideoId(videoId);
                var saveVideo = videoRepository.save(video);

                return "Video Added Successfully";
            }
            return "Video Already Exist";
        }catch (Exception e){
            throw new EtBadRequestException("An Error has occurred");
        }
    }

    @Override
    public CheckOutResponse CalculatePrice(List<VideoRequest> videoRequest) throws EtBadRequestException {
        try {
            Double totalPrice = 0.00;
            Double Price = 0.00;
            CheckOutResponse checkOutResponse = new CheckOutResponse();

            for (var video : videoRequest)
            {
                String videoTitle = video.getVideoTitle();
                var checkIfExist = videoRepository.findOneByvideoTitle(videoTitle);
                if (checkIfExist != null) {
                    if (checkIfExist.getVideoType() == com.learningjava.VideoRentalApi.Enums.videoTypeEnum.valueOf("REGULAR")){
                        var price = checkIfExist.getPrice() * video.getNumberOfDays();
                        Price = price;
                        totalPrice += price;
                    } else if(checkIfExist.getVideoType() == com.learningjava.VideoRentalApi.Enums.videoTypeEnum.valueOf("CHILDREN"))
                    {
                        var price = checkIfExist.getPrice() * video.getNumberOfDays() + (checkIfExist.getMaxAge() / 2);
                        Price = price;
                        totalPrice += price;
                    } else if(checkIfExist.getVideoType() == com.learningjava.VideoRentalApi.Enums.videoTypeEnum.valueOf("NEW_RELEASE")){
                        var price = checkIfExist.getPrice() * video.getNumberOfDays() - checkIfExist.getYearReleased();
                        Price  = price;
                        totalPrice += price;
                    }

                    checkOutResponse.setUserName(video.getUserName());

                    RentalHistory rentalHistory = new RentalHistory();
                    rentalHistory.setRentalID(UUID.randomUUID().toString());
                    rentalHistory.setRentalDays(video.getNumberOfDays());
                    rentalHistory.setTotalPrice(Price);
                    rentalHistory.setVideoId(checkIfExist.getVideoId());
                    rentalHistory.setTransactionDate(System.currentTimeMillis());
                    rentalHistory.setUserName(video.getUserName());

                    rentalHistoryRepository.save(rentalHistory);
                }

            }


            checkOutResponse.setTotalPrice(totalPrice + " Bir");

            return checkOutResponse;

        } catch (Exception e) {
            throw new EtBadRequestException("An Error has occurred");
        }
    }

    @Override
    public Video updateVideo(Video video) throws EtBadRequestException {
        try {
            if (video != null) {
                var getVideo = videoRepository.findByvideoId(video.getVideoId());

                if(getVideo != null){
                    CopyProperty.CopyObject(video , getVideo);
                    videoRepository.save(getVideo);
                    return getVideo;
                }
                return null;
            }
            return null;

        }catch (Exception e) {
            throw new EtBadRequestException("An Error has Occurred");
        }
    }

    @Override
    public String removeVideo(String VideoId) throws EtResourceNotFoundException {
        try {
            if(VideoId != null) {
                var getVideo = videoRepository.findByvideoId(VideoId);
                if(getVideo != null){
                    videoRepository.deleteByvideoId(VideoId);
                    return "Video Deleted";
                }
                return "Video does not exist";
            }
            throw new EtBadRequestException("UserId/CategoryId cannot be null");
        } catch (Exception e) {
            throw new EtBadRequestException("An Error has Occurred");
        }
    }



}
