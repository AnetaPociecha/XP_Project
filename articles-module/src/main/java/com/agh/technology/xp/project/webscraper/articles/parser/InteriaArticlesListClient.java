package com.agh.technology.xp.project.webscraper.articles.parser;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class InteriaArticlesListClient {

    private HttpClient httpClient;
    private ArticleHeadersParser articleHeadersParser;
    private String targetUrl;


    private InteriaArticlesListClient(HttpClient httpClient, ArticleHeadersParser articleHeadersParser, String targetUrl) {
        this.httpClient = httpClient;
        this.articleHeadersParser = articleHeadersParser;
        this.targetUrl = targetUrl;
    }

    public ArticleContainer fetchAndParse() throws IOException {
        Document doc = httpClient.fetchDocument(targetUrl);
        return articleHeadersParser.parseDocument(doc);
    }

    public static class InteriaArticlesListClientBuilder{
        private HttpClient httpClient;
        private ArticleHeadersParser articleHeadersParser;
        private String targetUrl;

        public InteriaArticlesListClientBuilder httpClient(HttpClient httpClient){
            this.httpClient = httpClient;
            return this;
        }

        public InteriaArticlesListClientBuilder articleHeadersParser(ArticleHeadersParser articleHeadersParser){
            this.articleHeadersParser = articleHeadersParser;
            return this;
        }
        public InteriaArticlesListClientBuilder targetUrl(String targetUrl){
            this.targetUrl = targetUrl;
            return this;
        }

        public InteriaArticlesListClient build(){
            return new InteriaArticlesListClient(this.httpClient, this.articleHeadersParser, this.targetUrl);
        }
    }
}
