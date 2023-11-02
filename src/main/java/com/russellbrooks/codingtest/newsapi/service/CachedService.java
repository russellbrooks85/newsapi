package com.russellbrooks.codingtest.newsapi.service;

import com.russellbrooks.codingtest.newsapi.model.NewsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class CachedService {
    private static final Logger logger
            = LoggerFactory.getLogger(CachedService.class);

    @Autowired
    WebClient webClient;
    @Value("${gnews.apikey}")
    String apiKey;


    /**
     *  Calls the gnews Search api, caching identical searches using the default caffeine cache at the service level.
     *  For a larger app where this service would have multiple replicas, we should use a better solution like redis
     * @param q This parameter allows you to specify your search keywords to find the news articles you are looking for. The keywords will be used to return the most relevant articles. It is possible to use logical operators with keywords, see the section on query syntax.
     * @param lang This parameter allows you to specify the language of the news articles returned by the API. You have to set as value the 2 letters code of the language you want to filter.
     * @param country This parameter allows you to specify the country where the news articles returned by the API were published, the contents of the articles are not necessarily related to the specified country. You have to set as value the 2 letters code of the country you want to filter.
     * @param max This parameter allows you to specify the number of news articles returned by the API. The minimum value of this parameter is 1 and the maximum value is 100. The value you can set depends on your subscription.
     * @param in This parameter allows you to choose in which attributes the keywords are searched. The attributes that can be set are title, description and content. It is possible to combine several attributes by separating them with a comma. e.g. title,description
     * @param nullable This parameter allows you to specify the attributes that you allow to return null values. The attributes that can be set are title, description and content. It is possible to combine several attributes by separating them with a comma. e.g. title,description
     * @param from This parameter allows you to filter the articles that have a publication date greater than or equal to the specified value. The date must respect the following format:YYYY-MM-DDThh:mm:ssTZD TZD = time zone designator, its value must always be Z (universal time) e.g. 2023-11-01T15:49:46Z
     * @param to This parameter allows you to filter the articles that have a publication date smaller than or equal to the specified value. The date must respect the following format: YYYY-MM-DDThh:mm:ssTZD  TZD = time zone designator, its value must always be Z (universal time) e.g. 2023-11-01T15:49:46Z
     * @param sortby This parameter allows you to choose with which type of sorting the articles should be returned. Two values are possible: publishedAt = sort by publication date, the articles with the most recent publication date are returned first. relevance = sort by best match to keywords, the articles with the best match are returned first
     * @param page (not supported) This parameter will only work if you have a paid subscription activated on your account. This parameter allows you to control the pagination of the results returned by the API. The paging behavior is closely related to the value of the max parameter. The first page is page 1, then you have to increment by 1 to go to the next page. Let's say that the value of the max parameter is 10, then the first page will contain the first 10 articles returned by the API (articles 1 to 10), page 2 will return the next 10 articles (articles 11 to 20), the behavior extends to page 3, 4,
     * @param expand (not supported) This parameter will only work if you have a paid subscription activated on your account. This parameter allows you to return in addition to other data, the full content of the articles. To get the full content of the articles, the parameter must be set to content
     * @return NewsReponse object result from gnews.
     */
    @Cacheable(value="search", key="{ #root.methodName, #q, #lang, #country, #max, #in, #nullable, #from, #to, #sortby}")
    public NewsResponse search(String q,
                        String lang,
                        String country,
                        String max,
                        String in,
                        String nullable,
                        String from,
                        String to,
                        String sortby,
                        String page,
                        String expand) throws Exception {
        logger.info("ENTER search - not cached");
        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/search")
                .queryParam("apikey",apiKey)
                .queryParamIfPresent("q", Optional.ofNullable(q))
                .queryParamIfPresent("lang", Optional.ofNullable(lang))
                .queryParamIfPresent("country", Optional.ofNullable(country))
                .queryParamIfPresent("max", Optional.ofNullable(max))
                .queryParamIfPresent("in", Optional.ofNullable(in))
                .queryParamIfPresent("nullable", Optional.ofNullable(nullable))
                .queryParamIfPresent("from", Optional.ofNullable(from))
                .queryParamIfPresent("to", Optional.ofNullable(to))
                .queryParamIfPresent("sortby", Optional.ofNullable(sortby))
                .queryParamIfPresent("page", Optional.ofNullable(page))
                .queryParamIfPresent("expand", Optional.ofNullable(expand))
                .build())
                .retrieve()
                .bodyToMono(NewsResponse.class).block();

    }

    /**
     *  Calls the gnews topHeadlines api, caching identical searches using the default caffeine cache at the service level.
     *  For a larger app where this service would have multiple replicas, we should use a better solution like redis
     * @param lang This parameter allows you to specify the language of the news articles returned by the API. You have to set as value the 2 letters code of the language you want to filter.
     * @param country This parameter allows you to specify the country where the news articles returned by the API were published, the contents of the articles are not necessarily related to the specified country. You have to set as value the 2 letters code of the country you want to filter.
     * @param max This parameter allows you to specify the number of news articles returned by the API. The minimum value of this parameter is 1 and the maximum value is 100. The value you can set depends on your subscription.
     * @param in This parameter allows you to choose in which attributes the keywords are searched. The attributes that can be set are title, description and content. It is possible to combine several attributes by separating them with a comma. e.g. title,description
     * @param nullable This parameter allows you to specify the attributes that you allow to return null values. The attributes that can be set are title, description and content. It is possible to combine several attributes by separating them with a comma. e.g. title,description
     * @param from This parameter allows you to filter the articles that have a publication date greater than or equal to the specified value. The date must respect the following format:YYYY-MM-DDThh:mm:ssTZD TZD = time zone designator, its value must always be Z (universal time) e.g. 2023-11-01T15:49:46Z
     * @param to This parameter allows you to filter the articles that have a publication date smaller than or equal to the specified value. The date must respect the following format: YYYY-MM-DDThh:mm:ssTZD  TZD = time zone designator, its value must always be Z (universal time) e.g. 2023-11-01T15:49:46Z
     * @param page (not supported) This parameter will only work if you have a paid subscription activated on your account. This parameter allows you to control the pagination of the results returned by the API. The paging behavior is closely related to the value of the max parameter. The first page is page 1, then you have to increment by 1 to go to the next page. Let's say that the value of the max parameter is 10, then the first page will contain the first 10 articles returned by the API (articles 1 to 10), page 2 will return the next 10 articles (articles 11 to 20), the behavior extends to page 3, 4,
     * @param expand (not supported) This parameter will only work if you have a paid subscription activated on your account. This parameter allows you to return in addition to other data, the full content of the articles. To get the full content of the articles, the parameter must be set to content
     * @return NewsReponse object result from gnews.
     */
    @Cacheable(value="top-headlines", key="{ #root.methodName, #lang, #country, #max, #in, #nullable, #from, #to}")
    public NewsResponse topHeadlines(String lang,
                               String country,
                               String max,
                               String in,
                               String nullable,
                               String from,
                               String to,
                               String page,
                               String expand) throws Exception {
        logger.info("ENTER topHeadlines - not cached");
        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/top-headlines")
                .queryParam("apikey",apiKey)
                .queryParamIfPresent("lang", Optional.ofNullable(lang))
                .queryParamIfPresent("country", Optional.ofNullable(country))
                .queryParamIfPresent("max", Optional.ofNullable(max))
                .queryParamIfPresent("in", Optional.ofNullable(in))
                .queryParamIfPresent("nullable", Optional.ofNullable(nullable))
                .queryParamIfPresent("from", Optional.ofNullable(from))
                .queryParamIfPresent("to", Optional.ofNullable(to))
                .queryParamIfPresent("page", Optional.ofNullable(page))
                .queryParamIfPresent("expand", Optional.ofNullable(expand))
                .build())
                .retrieve()
                .bodyToMono(NewsResponse.class)
                .block();

    }
}
