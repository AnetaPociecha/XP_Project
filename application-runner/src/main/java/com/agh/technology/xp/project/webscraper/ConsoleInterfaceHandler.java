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
import com.agh.technology.xp.project.webscraper.validate.UserInputValidator;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class ConsoleInterfaceHandler {

    private final IPrinter printer;
    private final IScanner scanner;
    private final InteriaArticlesListClient interiaArticlesListClient;
    private final ArticleDetailsClient articleDetailsClient;
    private final UserInputValidator userInputValidator = new UserInputValidator();

    private ConsoleInterfaceHandler(InteriaArticlesListClient parser, ArticleDetailsClient articleDetailsClient, IPrinter printer, IScanner scanner) {
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
            printer.print(e.getMessage());
            runCLI();
        }
    }

    private String selectArticle(ArticleSection sectionChoice) throws UserInputException {
        List<ArticleHeader> articleHeaders = sectionChoice.getArcticleHeaders();
        IntStream.range(0, articleHeaders.size()).forEach(
                articleCounter -> printer.print(articleCounter+1+ ". " +String.format("%s", articleHeaders.get(articleCounter).getTitle()))
        );

        printer.print("Wybierz artykuł z listy");
        Integer articleChoice = scanner.scanIntegerFromInput();
        userInputValidator.validate(articleChoice+1, sectionChoice.getArcticleHeaders().size()+1, "Wybrałeś artykuł który nie istnieje!");

        return sectionChoice.getArcticleHeaders().get(articleChoice - 1).getUrl();
    }

    private Integer selectSection(List<ArticleSection> sections) throws UserInputException {
        printer.print("Wybierz sekcję, której artykuły chcesz przeglądać:");

        IntStream.range(0, sections.size()).forEach(
                sectionCounter -> printer.print(sectionCounter + 1 + ". " + String.format("%s", sections.get(sectionCounter).getName()))
        );

        Integer choice = scanner.scanIntegerFromInput();
        userInputValidator.validate(choice + 1,  sections.size() + 1, "Wybrałeś sekcję która nie istnieje!");

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

    public static class ConsoleInterfaceHandlerBuilder {
        private InteriaArticlesListClient parser;
        private ArticleDetailsClient articleDetailsClient;
        private IPrinter printer;
        private IScanner scanner;

        public ConsoleInterfaceHandlerBuilder parser(InteriaArticlesListClient parser) {
            this.parser = parser;
            return this;
        }

        public ConsoleInterfaceHandlerBuilder articleDetailsClient(ArticleDetailsClient articleDetailsClient) {
            this.articleDetailsClient = articleDetailsClient;
            return this;
        }

        public ConsoleInterfaceHandlerBuilder printer(IPrinter printer) {
            this.printer = printer;
            return this;
        }

        public ConsoleInterfaceHandlerBuilder scanner(IScanner scanner) {
            this.scanner = scanner;
            return this;
        }

        public ConsoleInterfaceHandler build() {
            return new ConsoleInterfaceHandler(parser, articleDetailsClient, printer, scanner);
        }
    }
    
}
