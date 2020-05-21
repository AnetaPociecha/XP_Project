package com.agh.technology.xp.project.webscraper.articlescraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class InteriaArticleParser implements IArticleParser {
    public InteriaArticle parse(Document document){

        String title = parseHeadline(document);

        List<Element> paragraphs = parseBody(document);

        List<String> paragraphsContentStringList = paragraphs.stream().map(Element::text).collect(Collectors.toList());

        return new InteriaArticle(title, String.join("\n", paragraphsContentStringList));
    }

    private String parseHeadline(Document document) {
        Elements titleElements = document.getElementsByAttributeValue("itemprop", "name headline");
        return titleElements.text();
    }

    private List<Element> parseBody(Document document){
        Elements articleBody = document.getElementsByAttributeValue("itemprop", "articleBody");
        List<Element> paragraphs = articleBody.select("p");
        paragraphs.remove(1);
        return paragraphs;
    }

}
