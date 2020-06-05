package com.agh.technology.xp.project.webscraper.articlescraper;

public class ArticleClientAcceptanceTest {

    private String url;

    public ArticleClientAcceptanceTest(String url) {
        this.url = url;
    }

    public ArticleClientAcceptanceTest() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String title () throws HttpRequestException {
        IArticleParser parser = new InteriaArticleParser();
        ArticleDetailsClient validClient = new ArticleDetailsClient(new HttpClient(), parser);

        return validClient.getInteriaArticle(url).getTitle();
    }
}
