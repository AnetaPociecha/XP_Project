package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParser;

import java.io.IOException;

public class ApplicationDelegate {

    private ArticleHeadersParser parser;

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
