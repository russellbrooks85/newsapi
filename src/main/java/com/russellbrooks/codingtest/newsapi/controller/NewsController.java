package com.russellbrooks.codingtest.newsapi.controller;

import com.russellbrooks.codingtest.newsapi.model.Article;
import com.russellbrooks.codingtest.newsapi.model.NewsResponse;
import com.russellbrooks.codingtest.newsapi.service.NewsService;
import com.russellbrooks.codingtest.newsapi.util.Utilities;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "News API")
public class NewsController {
    private static final Logger logger
            = LoggerFactory.getLogger(NewsController.class);


    @Autowired
    NewsService newsService;


    @Operation(summary = "search by title", description="Simplified search by title operation returning A single news article in JSON Format")
    @GetMapping("/news/title/{title}")
    public Article simpleTitleSearch(@PathVariable("title") String title) throws Exception{
        return newsService.simpleTitleSearch(title);

    }


    @Operation(summary = "search by title", description="Simplified search by title operation returning A single news article the form of a simple html page")
    @GetMapping(value="/news/title/{title}/html", produces= MediaType.TEXT_HTML_VALUE)
    public String simpleTitleSearchHtml(@PathVariable("title") String title) throws Exception{
        return Utilities.articlesAsHtml(List.of(newsService.simpleTitleSearch(title)));
    }

    @Operation(summary = "search by keyword", description="Simplified search by keyword operation returning the max number of news articles specified in a simple JSON Format")
    @GetMapping("/news/keyword")
    public NewsResponse keywordSearch(@RequestParam(value="keyword", required=false)String keyword, @RequestParam(value="max", required=false) String max) throws Exception{

        return newsService.keywordSearch(keyword,max);
    }

    @Operation(summary = "search by keyword", description="Simplified search by keyword operation returning the max number of news articles specified in the form of a simple html page")
    @GetMapping(value="/news/keyword/html", produces= MediaType.TEXT_HTML_VALUE)
    public String keywordSearchHtml(@RequestParam(value="keyword", required=false) String keyword, @RequestParam(value="max", required=false) String max) throws Exception{

        return Utilities.articlesAsHtml(newsService.keywordSearch(keyword,max).getArticles());
    }

    @Operation(summary = "get top headlines", description="Simplified operation to get the current returning the max number of news articles specified in a simple JSON Format")
    @GetMapping("/news/top")
    public NewsResponse topHeadlines(@RequestParam(value="max", required=false) String max) throws Exception {

        return newsService.topHeadlines(max);
    }

    @Operation(summary = "get top headlines", description="Simplified operation to get the current returning the max number of news articles specified in the form of a simple html page")
    @GetMapping(value="/news/top/html", produces= MediaType.TEXT_HTML_VALUE)
    public String topHeadlinesHtml(@RequestParam(value="max", required=false) String max) throws Exception{

        return  Utilities.articlesAsHtml(newsService.topHeadlines(max).getArticles());
    }



    @Operation(summary = "get top headlines", description="Full operation to get the current top articles specified by all the available parameters in a simple JSON Format")
    @GetMapping(value="/news/topHeadlines")
    public NewsResponse topHeadlines(@RequestParam(value="lang", required=false)String lang,
                                     @RequestParam(value="country", required=false)String country,
                                     @RequestParam(value="max", required=false)String max,
                                     @RequestParam(value="in", required=false)String in,
                                     @RequestParam(value="nullable", required=false)String nullable,
                                     @RequestParam(value="from", required=false)String from,
                                     @RequestParam(value="to", required=false)String to) throws Exception {
        return newsService.topHeadlines(lang,country,max,in,nullable,from,to);


    }

    @Operation(summary = "get top headlines", description="Full operation to get the current top articles specified by all the available parameters in the form of a simple html page")
    @GetMapping(value="/news/topHeadlines/html", produces= MediaType.TEXT_HTML_VALUE)
    public String topHeadlinesHtml(@RequestParam(value="lang", required=false)String lang,
                                   @RequestParam(value="country", required=false)String country,
                                   @RequestParam(value="max", required=false)String max,
                                   @RequestParam(value="in", required=false)String in,
                                   @RequestParam(value="nullable", required=false)String nullable,
                                   @RequestParam(value="from", required=false)String from,
                                   @RequestParam(value="to", required=false)String to) throws Exception {
        return  Utilities.articlesAsHtml(newsService.topHeadlines(lang,country,max,in,nullable,from,to).getArticles());


    }


    @Operation(summary = "search all news", description="Full operation to search all articles specified by all the available parameters in a simple JSON Format")
    @GetMapping(value="/news/search")
    public NewsResponse search(@RequestParam(value="q", required=false)String q,
                               @RequestParam(value="lang", required=false)String lang,
                               @RequestParam(value="country", required=false)String country,
                               @RequestParam(value="max", required=false)String max,
                               @RequestParam(value="in", required=false)String in,
                               @RequestParam(value="nullable", required=false)String nullable,
                               @RequestParam(value="from", required=false)String from,
                               @RequestParam(value="to", required=false)String to,
                               @RequestParam(value="sortBy", required=false)String sortBy) throws Exception {
        return newsService.search(q,lang,country,max,in,nullable,from,to,sortBy);
    }

    @Operation(summary = "search all news", description="Full operation to search all articles specified by all the available parameters in the form of a simple html page")
    @GetMapping(value="/news/search/html", produces= MediaType.TEXT_HTML_VALUE)
    public String searchHtml(@RequestParam(value="q", required=false)String q,
                             @RequestParam(value="lang", required=false)String lang,
                             @RequestParam(value="country", required=false)String country,
                             @RequestParam(value="max", required=false)String max,
                             @RequestParam(value="in", required=false)String in,
                             @RequestParam(value="nullable", required=false)String nullable,
                             @RequestParam(value="from", required=false)String from,
                             @RequestParam(value="to", required=false)String to,
                             @RequestParam(value="sortBy", required=false)String sortBy) throws Exception {
        return Utilities.articlesAsHtml(newsService.search(q,lang,country,max,in,nullable,from,to,sortBy).getArticles());
    }

}
