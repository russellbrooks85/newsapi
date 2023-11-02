package com.russellbrooks.codingtest.newsapi.config;

import com.russellbrooks.codingtest.newsapi.exception.BadRequestException;
import com.russellbrooks.codingtest.newsapi.exception.NewsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    private static final Logger logger
            = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException e) {
        logger.error("Bad Request : returning 400 error : {}", e.getError());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getError().toString());
    }

    @ExceptionHandler(NewsException.class)
    public ResponseEntity<String> handleBadRequest(NewsException e) {
        logger.error("Error Processing Request : returning 500 error : {}", e.getError());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getError().toString());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        logger.error("Error Processing Request : returning 500 error : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No result found for search");
    }



    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getStatusCode(), ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
    }
}