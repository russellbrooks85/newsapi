package com.russellbrooks.codingtest.newsapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseError {
    String errorCode;
    String errorDesc;
}
