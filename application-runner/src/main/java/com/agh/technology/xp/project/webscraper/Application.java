package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.config.ConfigReader;

public class Application {


    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();

        String resourceUrl = configReader.readConfigFile().createUrl();
        configReader.readConfigFile();
        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler(
                new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                    .httpClient(new HttpClientImpl())
                    .articleHeadersParser(new ArticleHeadersParserImpl())
                    .build(),
                new InteriaArticleDetailsClient());
        delegate.runCLI();
    }
}
