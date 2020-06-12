package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articlescraper.HttpClient;
import com.agh.technology.xp.project.webscraper.articlescraper.ArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleParser;
import com.agh.technology.xp.project.webscraper.config.ConfigData;
import com.agh.technology.xp.project.webscraper.config.ConfigReader;
import com.agh.technology.xp.project.webscraper.io.CLIPrinter;
import com.agh.technology.xp.project.webscraper.io.CLIScanner;
import com.agh.technology.xp.project.webscraper.validate.UrlValidatorFacade;

import java.util.List;

public class Application {

    private static final String DEFAULT_TARGET_URL = "https://www.interia.pl";

    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();
        ConfigData configData = configReader.readConfigFile();

        String resourceUrl = configData.createUrl();
        List<String> sectionsToParse = configData.getSections();
        if (!UrlValidatorFacade.isValid(resourceUrl)) {
            resourceUrl = DEFAULT_TARGET_URL;
        }

        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler.ConsoleInterfaceHandlerBuilder()
                .parser(new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                    .httpClient(new HttpClientImpl())
                    .articleHeadersParser(new ArticleHeadersParserImpl(resourceUrl, sectionsToParse))
                    .targetUrl(resourceUrl)
                    .build())
                .articleDetailsClient(
                        new ArticleDetailsClient(new HttpClient(), new InteriaArticleParser()))
                .printer(new CLIPrinter())
                .scanner(new CLIScanner())
                .build();

        delegate.runCLI();
    }
}
