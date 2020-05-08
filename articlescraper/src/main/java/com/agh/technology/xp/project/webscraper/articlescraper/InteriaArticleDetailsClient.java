package com.agh.technology.xp.project.webscraper.articlescraper;

import org.jsoup.nodes.Document;

import java.io.IOException;

public class InteriaArticleDetailsClient {

    private IHttpClient httpClient;
    private InteriaArticleParser parser;

    public InteriaArticleDetailsClient() {
        httpClient = new HttpClient();
        parser = new InteriaArticleParser();
    }

    public InteriaArticleDetailsClient(IHttpClient httpClient) {
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
