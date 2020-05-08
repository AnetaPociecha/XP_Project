package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParser;

public class Application {


    public static void main(String[] args) {
        ApplicationDelegate delegate = new ApplicationDelegate(new ArticleHeadersParser());
        delegate.initializeCLI();
    }
}
