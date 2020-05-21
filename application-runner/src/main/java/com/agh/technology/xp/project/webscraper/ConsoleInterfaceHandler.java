package com.agh.technology.xp.project.webscraper;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articlescraper.IArticle;
import com.agh.technology.xp.project.webscraper.articlescraper.ArticleDetailsClient;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.exception.UserInputException;
import com.agh.technology.xp.project.webscraper.io.IPrinter;
import com.agh.technology.xp.project.webscraper.io.IScanner;

import java.io.IOException;
import java.util.List;

public class ConsoleInterfaceHandler {

    private final IPrinter printer;
    private final IScanner scanner;
    private final InteriaArticlesListClient interiaArticlesListClient;
    private final ArticleDetailsClient articleDetailsClient;

    ConsoleInterfaceHandler(InteriaArticlesListClient parser, ArticleDetailsClient articleDetailsClient, IPrinter printer, IScanner scanner) {
        this.interiaArticlesListClient = parser;
        this.articleDetailsClient = articleDetailsClient;
        this.printer = printer;
        this.scanner = scanner;
    }

    void runCLI() {
        try {
            printer.clearScreen();
            ArticleContainer container = interiaArticlesListClient.fetchAndParse();
            List<ArticleSection> sections = container.getAllSections();

            Integer choice = selectSection(sections);
            ArticleSection sectionChoice = sections.get(choice);
            printer.clearScreen();

            String articleUrlChoice = selectArticle(sectionChoice);
            IArticle article = articleDetailsClient.getInteriaArticle(articleUrlChoice);

            printer.clearScreen();
            displayArticle(article);

            shouldExitOrRerun();

        } catch (IOException e) {
            printer.print("Wystąpił problem z wyświetleniem wybranej treści, spróbuj ponownie\n");
            runCLI();
        }catch (UserInputException e){
            runCLI();
        }
    }

    private String selectArticle(ArticleSection sectionChoice) throws UserInputException {
        int articleCounter = 1;
        for(ArticleHeader header : sectionChoice.getArcticleHeaders()){
            printer.print(articleCounter+ ". " +String.format("%s", header.getTitle()));
            articleCounter += 1;
        }

        printer.print("Wybierz artykuł z listy");
        Integer articleChoice = scanner.scanIntegerFromInput();
        validateUserInput(articleChoice+1, sectionChoice.getArcticleHeaders().size()+1, "Wybrałeś artykuł który nie istnieje!");

        return sectionChoice.getArcticleHeaders().get(articleChoice - 1).getUrl();
    }

    private Integer selectSection(List<ArticleSection> sections) throws UserInputException {
        printer.print("Wybierz sekcję, której artykuły chcesz przeglądać:");

        int sectionCounter = 1;
        for(ArticleSection articleSection : sections){
            printer.print(sectionCounter + ". " + String.format("%s", articleSection.getName()));
            sectionCounter += 1;
        }

        Integer choice = scanner.scanIntegerFromInput();
        validateUserInput(choice + 1,  sections.size() + 1, "Wybrałeś sekcję która nie istnieje!");

        return choice - 1;
    }

    private void displayArticle(IArticle article) {
        printer.print(article.getContent());
    }

    private void shouldExitOrRerun(){
        printer.print("Aby zakończyć wpisz \"X\", aby wrócić do wyboru treści wpisz \"R\"");
        String whatNextChoice = scanner.nextLine().toLowerCase();
        if(whatNextChoice.equals("r")){
            runCLI();
        }else if (whatNextChoice.equals("x")){
            System.exit(0);
        } else{
            shouldExitOrRerun();
        }
    }
    
    private void validateUserInput(int userChoice, int max, String errorMessage) throws UserInputException {
        if(userChoice > max || userChoice < 1){
            printer.print(errorMessage);
            throw new UserInputException(errorMessage);
        }
    }
    
}
