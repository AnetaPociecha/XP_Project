package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParser;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleClient;

public class Application {


    public static void main(String[] args) {
        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler(new ArticleHeadersParser(), new InteriaArticleClient());
        delegate.runCLI();
    }
}
