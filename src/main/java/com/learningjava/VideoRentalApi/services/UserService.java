package com.learningjava.VideoRentalApi.services;

import com.learningjava.VideoRentalApi.entity.User;
import com.learningjava.VideoRentalApi.exceptions.EtAuthException;

import java.util.Optional;


public interface UserService {

    User validateUser(String email, String password) throws
            EtAuthException;

    Optional<User> registerUser(User user) throws
            EtAuthException;
}
