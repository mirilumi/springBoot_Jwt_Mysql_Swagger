package com.example.demo.exceptions;

import com.example.demo.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final ErrorCode errorCode;

    private final Date timestamp;

    public ErrorResponse(final String message, final ErrorCode errorCode, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp =  new java.util.Date();
    }
    public static ErrorResponse of(final String message, final ErrorCode errorCode, HttpStatus status) {
        return new ErrorResponse(message, errorCode, status);
    }
    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
