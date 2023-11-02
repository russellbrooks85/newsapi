package com.russellbrooks.codingtest.newsapi.exception;

import com.russellbrooks.codingtest.newsapi.model.ResponseError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NewsException extends Exception {
    ResponseError error;
    public NewsException(String desc) {
        error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), desc);
    }
}