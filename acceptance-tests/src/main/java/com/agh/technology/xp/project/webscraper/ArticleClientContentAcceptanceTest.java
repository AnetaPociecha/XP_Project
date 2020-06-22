package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
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

    public String content() throws HttpRequestException, InvalidGetterStrategyException {
        IArticleParser parser = new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig());
        ArticleDetailsClient validClient = new ArticleDetailsClient(new HttpClient(), parser);

        return validClient.getInteriaArticle(url).getContent();
    }
}
