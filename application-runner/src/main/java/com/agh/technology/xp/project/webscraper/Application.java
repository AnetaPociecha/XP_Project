package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.config.ArticleConfig;
import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.HeaderConfig;
import com.agh.technology.xp.project.webscraper.articles.config.SectionConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.AttributeGetter;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.GetterType;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.HTMLGetterStrategy;
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
import java.util.Arrays;

public class Application {

    private static final String DEFAULT_TARGET_URL = "https://www.interia.pl";

    public static void main(String[] args) throws IOException, ParseException, InvalidGetterStrategyException {
        String configFilename = args.length > 0 ? args[0] : "config.json";
        System.out.println(Arrays.deepToString(args));
        ConfigData configData = ConfigData.fromFile(configFilename);
        ArticleHeadersParserConfig articleHeadersParserConfig = configData.getHeadersParserConfig() != null ? configData.getHeadersParserConfig() : ArticleHeadersParserConfig.defaultConfig();
        String resourceUrl = configData.getURL();

        if (!UrlValidatorFacade.isValid(resourceUrl)) {
            resourceUrl = DEFAULT_TARGET_URL;
        }

        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler.ConsoleInterfaceHandlerBuilder()
                .parser(new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                        .httpClient(new HttpClientImpl())
                        .articleHeadersParser(new ArticleHeadersParserImpl(resourceUrl, articleHeadersParserConfig))
                        .targetUrl(resourceUrl)
                        .build())
                .articleDetailsClient(
                        new ArticleDetailsClient(new HttpClient(), new InteriaArticleParser(articleHeadersParserConfig)))
                .printer(new CLIPrinter())
                .scanner(new CLIScanner())
                .build();

        delegate.runCLI();
    }


}
