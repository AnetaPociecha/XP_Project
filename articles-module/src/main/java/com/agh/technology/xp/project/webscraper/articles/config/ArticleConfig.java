package com.agh.technology.xp.project.webscraper.articles.config;

public class ArticleConfig {

    private final String articleHeadlineSelector;

    private final String articleBodySelector;

    public ArticleConfig(String articleHeadlineSelector, String articleBodySelector) {
        this.articleHeadlineSelector = articleHeadlineSelector;
        this.articleBodySelector = articleBodySelector;
    }

    public String getArticleHeadlineSelector() {
        return articleHeadlineSelector;
    }

    public String getArticleBodySelector() {
        return articleBodySelector;
    }
}