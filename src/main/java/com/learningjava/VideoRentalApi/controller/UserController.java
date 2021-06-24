package com.learningjava.VideoRentalApi.controller;


import com.learningjava.VideoRentalApi.TokenHandler;
import com.learningjava.VideoRentalApi.entity.User;
import com.learningjava.VideoRentalApi.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
 private UserServiceImpl userService;      //service class injection
    @Autowired
     private TokenHandler tokenHandler;


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        //Map<String, String> map = new HashMap<>();
        //map.put("message", "loggedIn successfully");
        var token = tokenHandler.generateJWTToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        //Map<String, String> map = new HashMap<>();
        //map.put("message", "registered successfully");
        var token = tokenHandler.generateJWTToken(user);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }



}
