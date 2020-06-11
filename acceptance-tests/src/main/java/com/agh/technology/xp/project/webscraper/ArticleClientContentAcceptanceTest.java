package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articlescraper.*;

public class ArticleClientContentAcceptanceTest {

    private String url;

    public ArticleClientContentAcceptanceTest(String url) {
        this.url = url;
    }

    public ArticleClientContentAcceptanceTest() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String content() throws HttpRequestException {
        IArticleParser parser = new InteriaArticleParser();
        ArticleDetailsClient validClient = new ArticleDetailsClient(new HttpClient(), parser);

        return validClient.getInteriaArticle(url).getContent();
    }
}
