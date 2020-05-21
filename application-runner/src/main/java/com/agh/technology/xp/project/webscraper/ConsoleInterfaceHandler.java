package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticle;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.exception.UserInputException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterfaceHandler {

    private static Scanner scanner = new Scanner(System.in);
    private InteriaArticlesListClient interiaArticlesListClient;
    private InteriaArticleDetailsClient interiaArticleDetailsClient;

    ConsoleInterfaceHandler(InteriaArticlesListClient parser, InteriaArticleDetailsClient interiaClient) {
        this.interiaArticlesListClient = parser;
        this.interiaArticleDetailsClient = interiaClient;
    }

    void runCLI() {
        try {
            clearScreen();
            ArticleContainer container = interiaArticlesListClient.fetchAndParse("https://www.interia.pl/");
            System.out.println("Wybierz sekcję, której artykuły chcesz przeglądać:");
            List<ArticleSection> sections = container.getAllSections();
            sections.remove(0);

            int sectionCounter = 1;
            for(ArticleSection articleSection : sections){
                System.out.println(sectionCounter + ". " + String.format("%s", articleSection.getName()));
                sectionCounter += 1;
            }

            Integer choice = scanIntegerFromInput();
            validateUserInput(choice + 1,  sections.size() + 1, "Wybrałeś sekcję która nie istnieje!");
            ArticleSection sectionChoice = sections.get(choice - 1);
            clearScreen();

            int articleCounter = 1;
            for(ArticleHeader header : sectionChoice.getArcticleHeaders()){
                System.out.println(articleCounter+ ". " +String.format("%s", header.getTitle()));
                articleCounter += 1;
            }

            System.out.println("Wybierz artykuł z listy");
            Integer articleChoice = scanIntegerFromInput();
            validateUserInput(articleChoice+1, sectionChoice.getArcticleHeaders().size()+1, "Wybrałeś artykuł który nie istnieje!");

            String articleUrlChoice = sectionChoice.getArcticleHeaders().get(articleChoice - 1).getUrl();
            InteriaArticle article = interiaArticleDetailsClient.getInteriaArticle(articleUrlChoice);

            clearScreen();
            System.out.println(article.getContent());

            shouldExitOrRerun();

        } catch (IOException e) {
            System.out.println("Wystąpił problem z wyświetleniem wybranej treści, spróbuj ponownie\n");
            runCLI();
        }catch (UserInputException e){
            runCLI();
        }
    }
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void shouldExitOrRerun(){
        System.out.println("Aby zakończyć wpisz \"X\", aby wrócić do wyboru treści wpisz \"R\"");
        String whatNextChoice = scanner.nextLine().toLowerCase();
        if(whatNextChoice.equals("r")){
            runCLI();
        }else if (whatNextChoice.equals("x")){
            System.exit(0);
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


    private void validateUserInput(int userChoice, int max, String errorMessage) throws UserInputException {
        if(userChoice > max || userChoice < 1){
            System.out.println(errorMessage);
            throw new UserInputException(errorMessage);
        }
    }



}
