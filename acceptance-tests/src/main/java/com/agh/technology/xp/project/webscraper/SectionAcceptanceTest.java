package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.*;

import java.io.IOException;
import java.util.Optional;

public class SectionAcceptanceTest {

    private String sectionName;

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int articlesNumber() throws IOException, InvalidGetterStrategyException {
        String resourceUrl = "https://www.interia.pl";
        IArticleParser parser = new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig());
        InteriaArticlesListClient interiaArticlesListClient = new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                .httpClient(new HttpClientImpl())
                .articleHeadersParser(new ArticleHeadersParserImpl(resourceUrl, ArticleHeadersParserConfig.defaultConfig()))
                .targetUrl(resourceUrl)
                .build();

        ArticleContainer articleContainer = interiaArticlesListClient.fetchAndParse();

        Optional<ArticleSection> sectionOptional = articleContainer.getAllSections().stream().filter(x -> x.getName().equals(sectionName)).findAny();

        return sectionOptional.map(articleSection -> articleSection.getArcticleHeaders().size()).orElse(0);
    }

    public String articlesList() throws IOException, InvalidGetterStrategyException {
        String resourceUrl = "https://www.interia.pl";
        IArticleParser parser = new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig());
        InteriaArticlesListClient interiaArticlesListClient = new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                .httpClient(new HttpClientImpl())
                .articleHeadersParser(new ArticleHeadersParserImpl(resourceUrl, ArticleHeadersParserConfig.defaultConfig()))
                .targetUrl(resourceUrl)
                .build();

        ArticleContainer articleContainer = interiaArticlesListClient.fetchAndParse();

        Optional<ArticleSection> sectionOptional = articleContainer.getAllSections().stream().filter(x -> x.getName().equals(sectionName)).findAny();

        return sectionOptional.map(articleSection -> articleSection.getTitles().toString()).orElse("");
    }
}
