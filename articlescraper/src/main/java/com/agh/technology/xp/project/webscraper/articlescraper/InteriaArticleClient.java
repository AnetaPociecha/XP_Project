package com.agh.technology.xp.project.webscraper.articlescraper;

import org.jsoup.nodes.Document;

import java.io.IOException;

public class InteriaArticleClient {

    private IHttpClient httpClient;
    private InteriaArticleParser parser;

    public InteriaArticleClient() {
        httpClient = new HttpClient();
        parser = new InteriaArticleParser();
    }

    public InteriaArticleClient(IHttpClient httpClient) {
        this.httpClient = httpClient;
        parser = new InteriaArticleParser();
    }

    public InteriaArticle getInteriaArticle(String articleUrl) throws HttpRequestException {
        try {
            Document articleDocument = httpClient.getDocument(articleUrl);
            return parser.parse(articleDocument);
        } catch (IOException e) {
            throw new HttpRequestException(e.getMessage());
        }
    }
}
