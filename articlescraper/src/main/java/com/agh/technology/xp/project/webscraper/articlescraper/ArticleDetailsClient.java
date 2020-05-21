package com.agh.technology.xp.project.webscraper.articlescraper;

import org.jsoup.nodes.Document;

import java.io.IOException;

public class ArticleDetailsClient {

    private IHttpClient httpClient;

    private IArticleParser parser;

    public ArticleDetailsClient(IHttpClient httpClient, IArticleParser parser) {
        this.httpClient = httpClient;
        this.parser = parser;
    }

    public IArticle getInteriaArticle(String articleUrl) throws HttpRequestException {
        try {
            Document articleDocument = httpClient.getDocument(articleUrl);
            return parser.parse(articleDocument);
        } catch (IOException e) {
            throw new HttpRequestException(e.getMessage());
        }
    }
}
