package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParser;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticle;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleClient;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterfaceHandler {

    private ArticleHeadersParser parser;
    private ArticleContainer container;
    private static Scanner scanner = new Scanner(System.in);
    private InteriaArticleClient interiaClient;

    ConsoleInterfaceHandler(ArticleHeadersParser parser, InteriaArticleClient interiaClient) {
        this.parser = parser;
        this.interiaClient = interiaClient;
    }

    void initializeCLI() {
        try {
            this.container = parser.fetchAndParse("https://www.interia.pl/");
            System.out.println("Wybierz sekcję, której artykuły chcesz przeglądać:");
            List<ArticleSection> sections = container.getAllSections();
            sections.forEach(articleSection -> {
                System.out.println(String.format("%s", articleSection.getName()));
            });
            String choice = scanner.nextLine();
            ArticleSection sectionChoice = sections.get(Integer.parseInt(choice));
            Runtime.getRuntime().exec("clear");
            sectionChoice.getArcticleHeaders().forEach(header -> {
                System.out.println(String.format("%s", header.getTitle()));
            });
            System.out.println("Wybierz artykuł z listy");
            String articleChoice = scanner.nextLine();
            String articleUrlChoice = sectionChoice.getArcticleHeaders().get(Integer.parseInt(articleChoice)).getUrl();
            InteriaArticle article = interiaClient.downloadAndParseArticle(articleUrlChoice);
            Runtime.getRuntime().exec("clear");
            System.out.println(article.getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
