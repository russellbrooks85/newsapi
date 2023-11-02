package com.russellbrooks.codingtest.newsapi.util;

import com.russellbrooks.codingtest.newsapi.exception.BadRequestException;
import com.russellbrooks.codingtest.newsapi.exception.NewsException;
import com.russellbrooks.codingtest.newsapi.model.Article;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.russellbrooks.codingtest.newsapi.util.Constants.*;

public class Utilities {
    private static final Logger logger
            = LoggerFactory.getLogger(Utilities.class);


    /**
     * This is the simplest possible way to view the results in a readable format.  I decided to do this instead of spending several hours writing a React GUI
     * @param articles list of articles resulting from the search
     * @return html string that can be viewed in a browser easily
     * @throws NewsException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static String articlesAsHtml(List<Article> articles) throws NewsException {

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("""
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>Title</title>
                    </head>
                    <body>
                    <style media="all">

                    div {background: #e7e7e7; display: table; max-width: 200px; max-width: 200px; padding: 10px; margin: 0 auto 40px; border: 2px;  border-radius: 5px; border-style: ridge; border-color: blue}

                    </style>
                    """);
            for (Article art : articles) {
                sb.append("<div><h1><a href=\"")
                        .append(art.getUrl())
                        .append("\">")
                        .append(art.getTitle())
                        .append("</a></h1>")
                        .append("<p>")
                        .append(art.getDescription())
                        .append("</p>\n" +
                                "<img src=\"")
                        .append(art.getImage())
                        .append("\" alt=\"Image not found\" ></div>");


            }
            sb.append("</body>\n" +
                    "</html>");
            return sb.toString();
        } catch (Exception e) {
            logger.error("Error building html", e);
            throw new NewsException("Exception building html:"+e.getLocalizedMessage());
        }
    }

    /**
     * validates max parameter. throws BadRequestException when validation fails
     * @param max number of articles to return - must be a number
     * @throws BadRequestException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static void validateMax(String max) throws BadRequestException {
        if (!StringUtils.isEmpty(max) && !StringUtils.isNumeric(max)) throw new BadRequestException("Invalid max parameter - must be a number");
    }

    /**
     * validates keyword parameter. throws BadRequestException when validation fails
     * @param keyword string to search parameter - cannot be null
     * @throws BadRequestException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static void validateKeyword(String keyword) throws BadRequestException {
        if (StringUtils.isEmpty(keyword)) throw new BadRequestException("Invalid keyword parameter - cannot be null");
    }

    /**
     * validates lang parameter. throws BadRequestException when validation fails
     * @param lang filter on language parameter - compared with array of all valid languages
     * @throws BadRequestException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static void validateLang(String lang) throws BadRequestException {
        if (!StringUtils.isEmpty(lang) && !StringUtils.equalsAnyIgnoreCase(lang,LANGUAGES)) throw new BadRequestException("Invalid lang parameter - "+lang+" is not a valid language");
    }

    /**
     * validates country parameter. throws BadRequestException when validation fails
     * @param country  compared with array of all valid countries
     * @throws BadRequestException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static void validateCountry(String country) throws BadRequestException {
        if (!StringUtils.isEmpty(country) && !StringUtils.equalsAnyIgnoreCase(country,COUNTRIES)) throw new BadRequestException("Invalid country parameter - "+country+" is not a valid country");
    }

    /**
     * validates sort parameter. throws BadRequestException when validation fails
     * @param sort compared with array of all valid sort fields
     * @throws BadRequestException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static void validateSort(String sort) throws BadRequestException {
        if (!StringUtils.isEmpty(sort) && !StringUtils.equalsAnyIgnoreCase(sort,SORT)) throw new BadRequestException("Invalid sortBy parameter - "+sort+" is not a valid parameter. Use either publishedAt or relevance ");
    }

    /**
     * validates in/nullable parameters. throws BadRequestException when validation fails
     * @param fields compared with array of all valid sort fields
     * @throws BadRequestException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static void validateFields(String fields) throws BadRequestException {
        if (!StringUtils.isEmpty(fields)) {
            for (String field: fields.split(",")) {
                if (!StringUtils.equalsAnyIgnoreCase(field,FIELDS)) throw new BadRequestException("Invalid in/nullable parameter - "+field+" is not a valid fields");

            }
        }
    }

    /**
     * validates to/from parameters. throws BadRequestException when validation fails
     * @param dateTime must be a valid date in the correct format
     * @throws BadRequestException displays back a message in the error case - handled by the GlobalControllerExceptionHandler
     */
    public static void validateDateTime(String dateTime) throws BadRequestException {
        DateTimeFormatter dtf  = DateTimeFormatter.ISO_INSTANT;
        try {
            if (!StringUtils.isEmpty(dateTime)) dtf.parse(dateTime);
        } catch (Exception e) {
            throw new BadRequestException("Invalid to/from parameter - "+dateTime+"  - The date must respect the following format:\n" +
                    "YYYY-MM-DDThh:mm:ssTZD\n" +
                    "TZD = time zone designator, its value must always be Z (universal time)\n" +
                    "e.g. 2022-08-21T16:27:09Z");
        }

    }

}
