package com.example.MoneyDefender.exceptions;

public class SpringException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SpringException(String exMessage) {
        super(exMessage);
    }
}
