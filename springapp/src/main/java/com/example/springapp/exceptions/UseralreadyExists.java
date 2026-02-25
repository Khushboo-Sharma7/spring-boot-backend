package com.example.springapp.exceptions;

public class UseralreadyExists extends RuntimeException{
    public UseralreadyExists(String error)
    {
        super(error);
    }
}
