package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ApplicationDelegate {

    @Autowired
    private ArticleHeadersParser parser;

    public void testGetArticles() {
        try {
            ArticleContainer container = parser.fetchAndParse("https://www.interia.pl/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
