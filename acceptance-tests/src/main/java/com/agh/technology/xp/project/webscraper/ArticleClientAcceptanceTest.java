package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articlescraper.*;

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

    public String title () throws HttpRequestException, InvalidGetterStrategyException {
        IArticleParser parser = new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig());
        ArticleDetailsClient validClient = new ArticleDetailsClient(new HttpClient(), parser);

        return validClient.getInteriaArticle(url).getTitle();
    }
}
