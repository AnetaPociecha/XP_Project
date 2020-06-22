package com.agh.technology.xp.project.webscraper.articles.parser;

import com.agh.technology.xp.project.webscraper.articles.config.ArticleConfigurationException;
import com.agh.technology.xp.project.webscraper.articles.config.HeaderConfig;
import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.SectionConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.AttributeGetter;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.GetterType;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.HTMLGetterStrategy;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArticleHeadersParserImpl implements ArticleHeadersParser {

    private String targetUrl;
    private ArticleHeadersParserConfig articleHeadersParserConfig;


    public ArticleHeadersParserImpl(String targetUrl, ArticleHeadersParserConfig articleHeadersParserConfig) {
        this.targetUrl = targetUrl;
        this.articleHeadersParserConfig = articleHeadersParserConfig;
    }

    public ArticleContainer fetchAndParse() throws IOException {
        Document doc = Jsoup.connect(targetUrl).get();
        return parseDocument(doc);
    }

    public ArticleContainer parseDocument(Document doc) {
        Element bodyElem = doc.body();
        // If there are multiple elements with same path, first is taken into account as section root
        Stream<Element> sectionElements = articleHeadersParserConfig.sectionConfig.sectionsSelectors.stream().map(bodyElem::selectFirst);
        List<ArticleSection> sections = sectionElements.map(this::parseSection).collect(Collectors.toList());
        return new ArticleContainer(sections);
    }


    public ArticleSection parseSection(Element sectionElem) {
        String name = getSectionName(sectionElem);
        List<ArticleHeader> headers = getHeaders(sectionElem);

        return new ArticleSection(name, headers);
    }

    private List<ArticleHeader> getHeaders(Element sectionElem) {
        HeaderConfig headerConfig = this.articleHeadersParserConfig.headerConfig;
        List<String> urls = sectionElem.select(headerConfig.articleURLSelector).stream().map(elem -> elem.attr("href")).collect(Collectors.toList());
        List<String> titles = headerConfig.articleTitleGetterConfig.getElementsAttributes(headerConfig.articleTitleGetterConfig.getElements(sectionElem));
        if (urls.size() != titles.size()) {
            throw new ArticleConfigurationException("Article number and url number mismatch - check your selectors config for article URLs and headers");
        }

        return IntStream.range(0, urls.size())
                .mapToObj(index -> new ArticleHeader(titles.get(index), urls.get(index)))
                .collect(Collectors.toList());
    }

    private String getSectionName(Element sectionElem) {
        SectionConfig sectionConfig = articleHeadersParserConfig.sectionConfig;
        AttributeGetter sectionNameGetterConfig = sectionConfig.sectionNameGetterConfig;
        String sectionName = sectionNameGetterConfig.getElementAttribute(sectionElem);
        if (!sectionName.isEmpty()) {
            return sectionName;
        } else {
            return "Other (name not found)";
        }
    }
}

