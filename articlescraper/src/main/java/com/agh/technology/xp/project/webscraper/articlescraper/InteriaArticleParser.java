package com.agh.technology.xp.project.webscraper.articlescraper;

import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class InteriaArticleParser implements IArticleParser {

    private final ArticleHeadersParserConfig articleHeadersParserConfig;

    public InteriaArticleParser(ArticleHeadersParserConfig articleHeadersParserConfig) {
        this.articleHeadersParserConfig = articleHeadersParserConfig;
    }

    public InteriaArticle parse(Document document){

        String title = parseHeadline(document);

        List<Element> paragraphs = parseBody(document);

        List<String> paragraphsContentStringList = paragraphs.stream().map(Element::text).collect(Collectors.toList());
        String content = paragraphsContentStringList.isEmpty()
                ? "Wygląda na to, że podana konfiguracja selektorów nie umożliwia sparsowanie tego artykułu.\n" +
                "Sprawdź czy adres artykułu nie wskazuje na inny serwis.\n" +
                "url: " + document.baseUri()
                : String.join("\n", paragraphsContentStringList);

        return new InteriaArticle(title, content);
    }

    private String parseHeadline(Document document) {
        return document.select(articleHeadersParserConfig.articleConfig.getArticleHeadlineSelector()).text();
    }

    private List<Element> parseBody(Document document){
        return document.select(articleHeadersParserConfig.articleConfig.getArticleBodySelector());
    }

}
