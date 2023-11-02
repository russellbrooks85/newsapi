package com.russellbrooks.codingtest.newsapi.exception;

import com.russellbrooks.codingtest.newsapi.model.ResponseError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BadRequestException extends Exception {
    ResponseError error;
    public BadRequestException(String desc) {
        error = new ResponseError(HttpStatus.BAD_REQUEST.toString(), desc);
    }
}
