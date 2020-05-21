package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.config.ConfigReader;
import com.agh.technology.xp.project.webscraper.validate.UrlValidatorFacade;

public class Application {

    private static final String DEFAULT_TARGET_URL = "https://www.interia.pl";

    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();

        String resourceUrl = configReader.readConfigFile().createUrl();
        if(!UrlValidatorFacade.isValid(resourceUrl)){
            resourceUrl = DEFAULT_TARGET_URL;
            System.out.println(":)");
        }
        configReader.readConfigFile();
        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler(
                new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                    .httpClient(new HttpClientImpl())
                    .articleHeadersParser(new ArticleHeadersParserImpl())
                    .articleUrl(resourceUrl)
                    .build(),
                new InteriaArticleDetailsClient());
        delegate.runCLI();
    }
}
