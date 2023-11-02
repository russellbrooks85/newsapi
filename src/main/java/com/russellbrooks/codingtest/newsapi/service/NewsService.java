package com.russellbrooks.codingtest.newsapi.service;

import com.russellbrooks.codingtest.newsapi.model.Article;
import com.russellbrooks.codingtest.newsapi.model.NewsResponse;
import com.russellbrooks.codingtest.newsapi.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private static final Logger logger
            = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    CachedService serv;

    public Article simpleTitleSearch(String title) throws Exception {
        logger.info("ENTER simpleTitleSearch : {}", title);
        return serv.search(title,"en","us","1","title",null,null,null,"relevance",null,null)
                    .getArticles().get(0);
    }

    public NewsResponse keywordSearch(String keyword,String max) throws Exception {
        logger.info("ENTER keywordSearch : keyword={} max={}", keyword,max);
        Utilities.validateMax(max);
        Utilities.validateKeyword(keyword);

        return serv.search(keyword,"en","us",max,"title",null,null,null,null,null,null);
    }


    public NewsResponse topHeadlines(String max) throws Exception {
        logger.info("ENTER topHeadlines : max={}",max);
        Utilities.validateMax(max);
        return serv.topHeadlines("en","us",max,null,null,null,null,null,null);
    }


    public NewsResponse topHeadlines(String lang,
                                     String country,
                                     String max,
                                     String in,
                                     String nullable,
                                     String from,
                                     String to) throws Exception {
        logger.info("ENTER topHeadlines : lang={} country={} max={} in={} nullable={} from={} to={}",lang,country,max,in,nullable,from,to);

        Utilities.validateLang(lang);
        Utilities.validateCountry(country);
        Utilities.validateMax(max);
        Utilities.validateFields(in);
        Utilities.validateFields(nullable);
        Utilities.validateDateTime(from);
        Utilities.validateDateTime(to);
        return serv.topHeadlines(lang,country,max,in,nullable,from,to, null,null);


    }

    public NewsResponse search(String q,
                               String lang,
                               String country,
                               String max,
                               String in,
                               String nullable,
                               String from,
                               String to,
                               String sortBy) throws Exception {
        logger.info("ENTER topHeadlines : q={} lang={} country={} max={} in={} nullable={} from={} to={} sortby={}",q,lang,country,max,in,nullable,from,to,sortBy);
        Utilities.validateKeyword(q);
        Utilities.validateLang(lang);
        Utilities.validateCountry(country);
        Utilities.validateMax(max);
        Utilities.validateFields(in);
        Utilities.validateFields(nullable);
        Utilities.validateDateTime(from);
        Utilities.validateDateTime(to);
        Utilities.validateSort(sortBy);
        return serv.search(q,lang,country,max,in,nullable,from,to,sortBy, null,null);
    }




}
