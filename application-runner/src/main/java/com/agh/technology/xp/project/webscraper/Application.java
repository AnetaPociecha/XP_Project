package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articlescraper.HttpClient;
import com.agh.technology.xp.project.webscraper.articlescraper.ArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleParser;
import com.agh.technology.xp.project.webscraper.config.ConfigData;
import com.agh.technology.xp.project.webscraper.io.CLIPrinter;
import com.agh.technology.xp.project.webscraper.io.CLIScanner;
import com.agh.technology.xp.project.webscraper.validate.UrlValidatorFacade;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Application {

    private static final String DEFAULT_TARGET_URL = "https://www.interia.pl";

    public static void main(String[] args) throws IOException, ParseException, InvalidGetterStrategyException {
        ConfigData configData = ConfigData.fromFile("config.json");
        String resourceUrl = configData.getURL();

        if (!UrlValidatorFacade.isValid(resourceUrl)) {
            resourceUrl = DEFAULT_TARGET_URL;
        }

        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler.ConsoleInterfaceHandlerBuilder()
                .parser(new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                        .httpClient(new HttpClientImpl())
                        .articleHeadersParser(new ArticleHeadersParserImpl(resourceUrl, configData.getHeadersParserConfig()))
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
