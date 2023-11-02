# News Api
Author Russell Brooks

This is a simple implementation of a news api. I have provided a few simple search endpoints as well as endpoints that expose the full functionality of the downstream app.  There is no gui interface since the prompt did not call for one, but instead I made the endpoints all return in 2 different forms. First in the raw JSON format, and also a simple HTML page to display the news articles.

## Running the application
run as a java app normally from IDE, or run
```bash
mvn clean install
java -jar newsapi-1.0.0-SNAPSHOT.jar
```

Swagger UI URL
http://localhost:8080/swagger-ui/index.html

## Test cases
These can be clicked directly to open in the browser. You will be prompted for credentials the first time. enter user/pass: unencrypted / shouldbrencrypted .  Alternatively, you may also run with a curl command like the following:
```bash
curl -v -X GET "http://localhost:8080/news/title/hiring" -H "Authorization: Basic dW5lbmNyeXB0ZWQ6c2hvdWxkYnJlbmNyeXB0ZWQ="
```
### These are examples that return the articles in html format
http://localhost:8080/news/top/html?max=2

http://localhost:8080/news/keyword/html?keyword=hire&max=10

http://localhost:8080/news/top/html?max=2

http://localhost:8080/news/topHeadlines/html?lang=en&country=us&max=2&to=2023-11-01T23:01:06Z

http://localhost:8080/news/topHeadlines/html?lang=en&country=us&max=2&to=2023-10-01T23:01:06Z

http://localhost:8080/news/topHeadlines/html?max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z

http://localhost:8080/news/search/html?q=Trial&max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z

### These are examples that return the articles in JSON format
http://localhost:8080/news/title/hire%20me

http://localhost:8080/news/top?max=2

http://localhost:8080/news/keyword?keyword=hire&max=2

http://localhost:8080/news/top?max=2

http://localhost:8080/news/topHeadlines?lang=en&country=us&max=2&to=2023-11-01T23:01:06Z

http://localhost:8080/news/topHeadlines?lang=en&country=us&max=2&to=2023-10-01T23:01:06Z

http://localhost:8080/news/topHeadlines?max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z

http://localhost:8080/news/search?q=Trial&max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z

### These are some error cases
keyword search - error case - missing keyword
http://localhost:8080/news/keyword/html?max=2

top headlines - error case -invalid max
http://localhost:8080/news/topHeadlines/html?lang=en&country=us&max=TWO&to=2023-10-01T23:01:06Z

top headlines - error case -invalid date
http://localhost:8080/news/topHeadlines/html?max=5&from=2023-105-01T23:01:06Z&to=2023-10-05T23:01:06Z

top headlines - error case -invalid country
http://localhost:8080/news/topHeadlines/html?max=5&country=xx

top headlines - error case -invalid lang
http://localhost:8080/news/topHeadlines/html?max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z&lang=zz

search - error case - missing keyword
http://localhost:8080/news/search/html?max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z

#### Error cases Repeated for json endpoints

keyword search - error case - missing keyword
http://localhost:8080/news/keyword?max=2

top headlines - error case -invalid max
http://localhost:8080/news/topHeadlines?lang=en&country=us&max=TWO&to=2023-10-01T23:01:06Z

top headlines - error case -invalid date
http://localhost:8080/news/topHeadlines?max=5&from=2023-105-01T23:01:06Z&to=2023-10-05T23:01:06Z

top headlines - error case -invalid country
http://localhost:8080/news/topHeadlines?max=5&country=xx

top headlines - error case -invalid lang
http://localhost:8080/news/topHeadlines?max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z&lang=zz

search - error case - missing keyword
http://localhost:8080/news/search?max=5&from=2023-10-01T23:01:06Z&to=2023-10-05T23:01:06Z



## Error codes

400	Bad Request -- Detailed message explaining the problem with the request

401	Unauthorized -- Your API key is wrong.

403	Forbidden -- You have reached your daily quota, the next reset is at 00:00 UTC.

429	Too Many Requests -- You have made more requests per second than you are allowed.

500	Internal Server Error -- We had a problem with our server. Try again later.

503	Service Unavailable -- We're temporarily offline for maintenance. Please try again later.



# Coding Test Prompt

This is the prompt that this project was written for:
## Project instructions
Your task is to create a simple REST API framework that interacts with a public news API for fetching articles. For the back-end, you have to use the Java language and Spring framework. For example, you can use the GNews API and then create your own API service, with documentation, that interacts with this API for fetching articles.



You API should have a few basic methods like, fetching N news articles, finding a news article with a specific title or author, and searching by keywords. Include a cache in your API service as well so users are not fetching the same things over and over.



## How to submit
Upload your completed project to your GitHub, and then paste a link to the repository below in the form along with any comments you have about your solution. Please include instructions on how to run the application in your README.md.

