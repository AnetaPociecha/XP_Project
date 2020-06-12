package com.agh.technology.xp.project.webscraper.articles.parser;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArticleHeadersParserImpl implements ArticleHeadersParser {
    private final List<String> sectionsSelectors;
    private String targetUrl;

    public ArticleHeadersParserImpl(String targetUrl, List<String> sections) {
        this.sectionsSelectors = sections;
        this.targetUrl = targetUrl;
    }

    public ArticleContainer fetchAndParse() throws IOException {
        Document doc = Jsoup.connect(targetUrl).get();
        return parseDocument(doc);
    }

    public ArticleContainer parseDocument(Document doc) {
        Element bodyElem = doc.body();
        // If there are multiple elements with same path, first is taken into account as section root
        Stream<Element> sectionElements = sectionsSelectors.stream().map(bodyElem::selectFirst);
        List<ArticleSection> sections = sectionElements.map(this::parseSection).collect(Collectors.toList());
        return new ArticleContainer(sections);
    }


    public ArticleSection parseSection(Element sectionElem) {
        String name = !sectionElem.select("a.header-a").isEmpty() ? sectionElem.select("a.header-a").first().attr("title") : "extra";
        List<ArticleHeader> headers = sectionElem.select("a.news-a, a.articles-a, a.news-one-a, a.special-a, a.special-triple-a, a.news-gallery-a, a.tiles-a").stream()
                .map(elem -> new ArticleHeader(elem.attr("title"), elem.attr("href")))
                .collect(Collectors.toList());

        return new ArticleSection(name, headers);
    }
}

