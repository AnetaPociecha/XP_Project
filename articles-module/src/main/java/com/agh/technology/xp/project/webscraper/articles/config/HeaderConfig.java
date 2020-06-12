package com.agh.technology.xp.project.webscraper.articles.config;


import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.AttributeGetter;

public class HeaderConfig {
    public String articleURLSelector;

    public AttributeGetter articleTitleGetterConfig;

    public HeaderConfig(String articleURLSelector, AttributeGetter articleTitleGetterConfig) {
        this.articleURLSelector = articleURLSelector;
        this.articleTitleGetterConfig = articleTitleGetterConfig;
    }
}
