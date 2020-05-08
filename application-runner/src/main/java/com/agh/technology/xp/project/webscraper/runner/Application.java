package com.agh.technology.xp.project.webscraper.runner;


import com.agh.technology.xp.project.webscraper.runner.articles.parser.ArticleHeadersParser;

public class Application {


    public static void main(String[] args) {
        ApplicationDelegate delegate = new ApplicationDelegate(new ArticleHeadersParser());
        delegate.testGetArticles();
    }
}
