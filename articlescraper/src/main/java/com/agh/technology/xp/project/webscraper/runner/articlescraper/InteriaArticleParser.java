package com.agh.technology.xp.project.webscraper.runner.articlescraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class InteriaArticleParser {
    public InteriaArticle parse(Document document){
        String title = document.title();

        Elements articleBody = document.getElementsByAttributeValue("itemprop", "articleBody");
        List<Element> paragraphs = articleBody.select("p");
        paragraphs.remove(1);

        List<String> paragraphsContentStringList = paragraphs.stream().map(Element::text).collect(Collectors.toList());

        return new InteriaArticle(title, String.join("\n", paragraphsContentStringList));
    }
}
