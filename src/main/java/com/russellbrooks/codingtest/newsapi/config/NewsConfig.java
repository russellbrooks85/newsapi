package com.russellbrooks.codingtest.newsapi.config;


import com.russellbrooks.codingtest.newsapi.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableCaching
public class NewsConfig {
    private static final Logger logger
            = LoggerFactory.getLogger(NewsService.class);

    @Value("${gnews.baseUrl}")
    String baseUrl;
    @Value("${gnews.apikey}")
    String apiKey;

    @Bean
    WebClient webClient() {
        logger.debug("baseUrl:{}",baseUrl);
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
