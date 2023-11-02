package com.russellbrooks.codingtest.newsapi.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class NewsResponse {
    BigInteger totalArticles;
    List<Article> articles;
}
