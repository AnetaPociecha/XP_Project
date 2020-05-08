package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticleClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleDetailsClient;

public class Application {


    public static void main(String[] args) {
        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler(new InteriaArticleClient(), new InteriaArticleDetailsClient());
        delegate.runCLI();
    }
}
