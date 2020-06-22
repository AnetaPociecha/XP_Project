package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.ArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.articlescraper.HttpClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleParser;
import com.agh.technology.xp.project.webscraper.io.CLIPrinter;
import com.agh.technology.xp.project.webscraper.io.IScanner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInterfaceHandlerTest {

    @Test
    void runCLI() throws InvalidGetterStrategyException {
        String resourceUrl = "https://www.interia.pl";
        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler.ConsoleInterfaceHandlerBuilder()
                .parser(new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                        .httpClient(new HttpClientImpl())
                        .articleHeadersParser(new ArticleHeadersParserImpl(resourceUrl, null))
                        .targetUrl(resourceUrl)
                        .build())
                .articleDetailsClient(
                        new ArticleDetailsClient(new HttpClient(), new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig())))
                .printer(new CLIPrinter())
                .scanner(new MockScanner())
                .build();

        delegate.runCLI();
    }

    static class MockScanner implements IScanner {

        static int round = 0;

        @Override
        public Integer scanIntegerFromInput() {
            return round == 0 ? 1 : 2;
        }

        @Override
        public String nextLine() {
            return round++ == 0 ? "R" : "X";
        }
    }
}