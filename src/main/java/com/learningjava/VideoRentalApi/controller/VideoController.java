package com.learningjava.VideoRentalApi.controller;


import com.learningjava.VideoRentalApi.entity.DTOs.VideoRequest;
import com.learningjava.VideoRentalApi.entity.Video;
import com.learningjava.VideoRentalApi.services.VideoServiceImpl;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    VideoServiceImpl videoService;

//    @GetMapping("")
//    public String getAllCategories(HttpServletRequest request) {
//        int userId = (Integer) request.getAttribute("userId");
//
//        return "Authenticated: UserId: " + userId;
//    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> AddVideo (@RequestBody Video video) {
        var resp = videoService.addVideo(video);
        Map<String, String> map = new HashMap<>();
        map.put("message", resp);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Var>> UpdateVideo (@RequestBody Video video) {
        var videoResp = videoService.updateVideo(video);
        if(videoResp != null) {
            Map<String, Video> map = new HashMap<>();
            map.put("Result", videoResp);
            return new ResponseEntity(map, HttpStatus.CREATED);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "Video Not Found");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllVideos")
    public ResponseEntity<Map<String, Page<Video>>> GetCategory(Pageable page) {
        var videos= videoService.fetchAllVideos(page);
        if (videos != null){
            Map<String, Page<Video>> map = new HashMap<>();
            map.put("Result", videos);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "No Videos");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }
//
    @GetMapping("/getVideoByTitle")
    public ResponseEntity<Map<String, Video>> GetVideoByTitle(@RequestParam String videoTitle) {
        var video= videoService.fetchVideoByTitle(videoTitle);
        if (video != null){
            Map<String, Video> map = new HashMap<>();
            map.put("Result", video);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "No Result");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getVideoByType")
    public ResponseEntity<Map<String, Page<Video>>> GetVideoByType(Pageable page, @RequestParam String videoType) {
        var video= videoService.fetchVideoByType(videoType, page);
        if (video != null){
            Map<String, Page<Video>> map = new HashMap<>();
            map.put("Result", video);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "No Result");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }
//
    @GetMapping("/getVideoByGenre")
    public ResponseEntity<Map<String, Page<Video>>> GetVideoByGenre(Pageable page, @RequestParam String videoGenre) {
    var video= videoService.fetchVideoByGenre(videoGenre, page);
    if (video != null){
        Map<String, Page<Video>> map = new HashMap<>();
        map.put("Result", video);
        return new ResponseEntity(map, HttpStatus.OK);
    }
    Map<String, String> map = new HashMap<>();
    map.put("Result", "No Result");
    return new ResponseEntity(map, HttpStatus.NOT_FOUND);
}
    @DeleteMapping("/RemoveVideoById")
    public ResponseEntity<Map<String, Var>> DeleteVideo(@RequestParam String videoId) {
        var video= videoService.removeVideo(videoId);
        if (video != "Video Deleted"){
            Map<String, String> map = new HashMap<>();
            map.put("Result", video);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", video);
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }
//
    @PostMapping("/CheckOut")
    public ResponseEntity<Map<String, Object>> AddVideo (@RequestBody List<VideoRequest> video) {
    var totalPrice = videoService.CalculatePrice(video);
    Map<String, Object> map = new HashMap<>();
    map.put("message", totalPrice);
    return new ResponseEntity(map, HttpStatus.OK);
}
//    @DeleteMapping("/RemoveCatsById")
//    public ResponseEntity<Map<String, Var>> DeleteCategories(@RequestParam Integer userId) {
//        var cat= categoryService.removeCategories(userId);
//        if (cat != "Categories deleted"){
//            Map<String, String> map = new HashMap<>();
//            map.put("Result", cat);
//            return new ResponseEntity(map, HttpStatus.OK);
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put("Result", cat);
//        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
//    }

}
