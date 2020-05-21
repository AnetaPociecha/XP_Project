package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articlescraper.HttpClient;
import com.agh.technology.xp.project.webscraper.articlescraper.ArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleParser;
import com.agh.technology.xp.project.webscraper.config.ConfigReader;
import com.agh.technology.xp.project.webscraper.io.CLIPrinter;
import com.agh.technology.xp.project.webscraper.io.CLIScanner;
import com.agh.technology.xp.project.webscraper.validate.UrlValidatorFacade;

public class Application {

    private static final String DEFAULT_TARGET_URL = "https://www.interia.pl";

    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();

        String resourceUrl = configReader.readConfigFile().createUrl();
        if (!UrlValidatorFacade.isValid(resourceUrl)) {
            resourceUrl = DEFAULT_TARGET_URL;
        }

        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler.ConsoleInterfaceHandlerBuilder()
                .parser(new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                    .httpClient(new HttpClientImpl())
                    .articleHeadersParser(new ArticleHeadersParserImpl(resourceUrl))
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
