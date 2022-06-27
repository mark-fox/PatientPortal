package com.markfox.patientmanager.exceptions;

// Custom Exception class for catching exceptions and passing custom message
public class MyException extends Exception {
    public MyException(String error) {
        super(error);
    }
}
