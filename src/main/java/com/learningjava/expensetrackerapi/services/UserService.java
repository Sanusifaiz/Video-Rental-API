package com.learningjava.expensetrackerapi.services;

import com.learningjava.expensetrackerapi.entity.User;
import com.learningjava.expensetrackerapi.exceptions.EtAuthException;

import java.util.Optional;


public interface UserService {

    User validateUser(String email, String password) throws
            EtAuthException;

    Optional<User> registerUser(User user) throws
            EtAuthException;
}
