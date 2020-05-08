package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ApplicationDelegate {

    private ArticleHeadersParser parser;
    private ArticleContainer container;
    private static Scanner scanner = new Scanner(System.in);

    ApplicationDelegate(ArticleHeadersParser parser) {
        this.parser = parser;
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
            sectionChoice.getTitles().forEach(title -> {
                System.out.println(String.format("%s", title));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
