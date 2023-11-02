package com.russellbrooks.codingtest.newsapi.controller;

import com.russellbrooks.codingtest.newsapi.model.Article;
import com.russellbrooks.codingtest.newsapi.model.NewsResponse;
import com.russellbrooks.codingtest.newsapi.service.NewsService;
import com.russellbrooks.codingtest.newsapi.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    private static final Logger logger
            = LoggerFactory.getLogger(NewsController.class);


    @Autowired
    NewsService newsService;


    @GetMapping("/news/title/{title}")
    public Article simpleTitleSearch(@PathVariable("title") String title) throws Exception{
        return newsService.simpleTitleSearch(title);

    }

    @GetMapping("/news/keyword")
    public NewsResponse keywordSearch(@RequestParam(value="keyword", required=false)String keyword, @RequestParam(value="max", required=false) String max) throws Exception{

        return newsService.keywordSearch(keyword,max);
    }

    @GetMapping(value="/news/keyword/html", produces= MediaType.TEXT_HTML_VALUE)
    public String keywordSearchHtml(@RequestParam(value="keyword", required=false) String keyword, @RequestParam(value="max", required=false) String max) throws Exception{

        return Utilities.articlesAsHtml(newsService.keywordSearch(keyword,max).getArticles());
    }

    @GetMapping("/news/top")
    public NewsResponse topHeadlines(@RequestParam(value="max", required=false) String max) throws Exception{

        return newsService.topHeadlines(max);
    }

    @GetMapping(value="/news/top/html", produces= MediaType.TEXT_HTML_VALUE)
    public String topHeadlinesHtml(@RequestParam(value="max", required=false) String max) throws Exception{

        return  Utilities.articlesAsHtml(newsService.topHeadlines(max).getArticles());
    }



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
