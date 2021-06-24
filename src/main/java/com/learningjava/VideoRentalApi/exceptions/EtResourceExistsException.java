package com.learningjava.VideoRentalApi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EtResourceExistsException extends RuntimeException{

    public EtResourceExistsException(String message) {
        super(message);
    }
}
