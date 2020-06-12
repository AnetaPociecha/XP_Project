package com.agh.technology.xp.project.webscraper.articles.config;


import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.AttributeGetter;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.GetterType;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.HTMLGetterStrategy;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;

import java.util.Arrays;
import java.util.List;

public class ArticleHeadersParserConfig {
    public SectionConfig sectionConfig;
    public HeaderConfig headerConfig;
    public ArticleConfig articleConfig;

    public ArticleHeadersParserConfig(SectionConfig sectionConfig, HeaderConfig headerConfig, ArticleConfig articleConfig) {
        this.sectionConfig = sectionConfig;
        this.headerConfig = headerConfig;
        this.articleConfig = articleConfig;
    }

    public static ArticleHeadersParserConfig defaultConfig() throws InvalidGetterStrategyException {
        List<String> sectionsSelectors = Arrays.asList(
                "#facts",
                "#business",
                "#sport",
                "#automotive",
                "#technologies",
                "#tiles"
        );

        HTMLGetterStrategy sectionNameAttributeGetterStrategy = new HTMLGetterStrategy(GetterType.ATTRIBUTE, "title", "Inne");
        AttributeGetter sectionNameGetterConfig = new AttributeGetter("a.header-a", sectionNameAttributeGetterStrategy);

        String urlSelector = "a.news-a, a.articles-a, a.news-one-a, a.special-a, a.special-triple-a, a.news-gallery-a, a.tiles-a";

        HTMLGetterStrategy articleTitleAttributeGetterStrategy = new HTMLGetterStrategy(GetterType.ATTRIBUTE, "title", null);
        AttributeGetter articleTitleGetterConfig = new AttributeGetter("a.news-a, a.articles-a, a.news-one-a, a.special-a, a.special-triple-a, a.news-gallery-a, a.tiles-a" , articleTitleAttributeGetterStrategy);

        String headlineSelector = "[itemprop='name headline']";
        String bodySelector = "[itemprop=articleBody] > p";

        return new ArticleHeadersParserConfig(
                new SectionConfig(sectionsSelectors, sectionNameGetterConfig),
                new HeaderConfig(urlSelector, articleTitleGetterConfig),
                new ArticleConfig(headlineSelector, bodySelector)
        );
    }
}
