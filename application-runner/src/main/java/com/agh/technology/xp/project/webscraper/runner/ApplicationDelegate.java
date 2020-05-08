package com.agh.technology.xp.project.webscraper.runner;

import com.agh.technology.xp.project.webscraper.runner.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.runner.articles.parser.ArticleHeadersParser;

import java.io.IOException;

public class ApplicationDelegate {

    private ArticleHeadersParser parser;

    @SuppressWarnings("unused")
    ApplicationDelegate(){}

    ApplicationDelegate(ArticleHeadersParser parser) {
        this.parser = parser;
    }

    void testGetArticles() {
        try {
            ArticleContainer container = parser.fetchAndParse("https://www.interia.pl/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
