package com.agh.technology.xp.project.webscraper.articles.parser;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArticleHeadersParserImpl implements ArticleHeadersParser {
    private static final List<String> sectionsToParse = Arrays.asList("special", "facts", "business", "sport", "automotive", "technologies", "tiles");

    @Override
    public ArticleContainer parseDocument(Document document){
        Stream<Element> sectionElements = sectionsToParse.stream().map(document::getElementById);
        List<ArticleSection> sections = sectionElements.map(this::parseSection).collect(Collectors.toList());
        return new ArticleContainer(sections);
    }

    public ArticleSection parseSection(Element sectionElem){
        String name = !sectionElem.select("a.header-a").isEmpty() ? sectionElem.select("a.header-a").first().attr("title") : "extra";
        List<ArticleHeader> headers = sectionElem.select("a.news-a, a.articles-a, a.news-one-a, a.special-a, a.news-gallery-a, a.tiles-a").stream()
                .map(elem -> new ArticleHeader(elem.attr("title"), elem.attr("href")))
                .collect(Collectors.toList());

        return new ArticleSection(name, headers);
    }
}
