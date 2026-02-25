package com.example.springapp.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String error)
    {
        super(error);
    }
}
