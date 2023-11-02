package com.russellbrooks.codingtest.newsapi.model;

import lombok.Data;
import java.time.Instant;

@Data
public class Article {
    String title;
    String description;
    String content;
    String url;
    String image;
    Instant publishedAt;
    Source source;

}
