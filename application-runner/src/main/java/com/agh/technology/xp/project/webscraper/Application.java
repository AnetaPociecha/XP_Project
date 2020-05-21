package com.agh.technology.xp.project.webscraper;


import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticleClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.config.ConfigReader;

public class Application {


    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();

        String resourceUrl = configReader.readConfigFile().createUrl();
        configReader.readConfigFile();
        ConsoleInterfaceHandler delegate = new ConsoleInterfaceHandler(new InteriaArticleClient(), new InteriaArticleDetailsClient());
        delegate.runCLI();
    }
}
