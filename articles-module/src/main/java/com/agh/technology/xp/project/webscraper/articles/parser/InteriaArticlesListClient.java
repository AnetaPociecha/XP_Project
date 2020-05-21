package com.agh.technology.xp.project.webscraper.articles.parser;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class InteriaArticlesListClient {

    private HttpClient httpClient;
    private ArticleHeadersParser articleHeadersParser;

    public InteriaArticlesListClient(){
        this.httpClient = new HttpClientImpl();
        this.articleHeadersParser = new ArticleHeadersParserImpl();
    }

    public InteriaArticlesListClient(HttpClient httpClient, ArticleHeadersParser articleHeadersParser) {
        this.httpClient = httpClient;
        this.articleHeadersParser = articleHeadersParser;
    }

    public ArticleContainer fetchAndParse(String targetUrl) throws IOException {
        Document doc = httpClient.fetchDocument(targetUrl);
        return articleHeadersParser.parseDocument(doc);
    }

}
