package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
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
            clearScreen();
            this.container = parser.fetchAndParse("https://www.interia.pl/");
            System.out.println("Wybierz sekcję, której artykuły chcesz przeglądać:");
            List<ArticleSection> sections = container.getAllSections();
            sections.remove(0);

            int sectionCounter = 1;
            for(ArticleSection articleSection : sections){
                System.out.println(sectionCounter + ". " + String.format("%s", articleSection.getName()));
                sectionCounter += 1;
            }

            Integer choice = scanIntegerFromInput();
            ArticleSection sectionChoice = sections.get(choice - 1);
            //Runtime.getRuntime().exec("cls");
            clearScreen();

            int articleCounter = 1;
            for(ArticleHeader header : sectionChoice.getArcticleHeaders()){
                System.out.println(articleCounter+ ". " +String.format("%s", header.getTitle()));
                articleCounter += 1;
            }

            System.out.println("Wybierz artykuł z listy");
            Integer articleChoice = scanIntegerFromInput();
            String articleUrlChoice = sectionChoice.getArcticleHeaders().get(articleChoice - 1).getUrl();
            InteriaArticle article = interiaClient.downloadAndParseArticle(articleUrlChoice);
//            Runtime.getRuntime().exec("cls");
            clearScreen();
            System.out.println(article.getContent());

            System.out.println("");
            shouldExitOrRerun();

        } catch (IOException e) {
            System.out.println("Wystąpił problem z wyświetleniem wybranej treści, spróbuj ponownie\n");
            initializeCLI();
        }catch (IndexOutOfBoundsException e){
            System.out.println("Wybrałeś artykuł który nie istnieje, spróbuj ponownie\n");
            initializeCLI();
        }
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void shouldExitOrRerun(){
        System.out.println("Aby zakończyć wpisz \"X\", aby wrócić do wyboru treści wpisz \"R\"");
        String whatNextChoice = scanner.nextLine();
        if(whatNextChoice.equals("R") || whatNextChoice.equals("r")){
            initializeCLI();
        }else if (whatNextChoice.equals("X") || whatNextChoice.equals("x")){
            return;
        } else{
            shouldExitOrRerun();
        }
    }

    private Integer scanIntegerFromInput(){
        try{
            String scannedInput = scanner.nextLine();
            return Integer.parseInt(scannedInput);
        } catch (NumberFormatException e){
            System.out.println("Wpisana wartość nie jest liczbą, spróbuj ponownie");
            return scanIntegerFromInput();
        }
    }

}
