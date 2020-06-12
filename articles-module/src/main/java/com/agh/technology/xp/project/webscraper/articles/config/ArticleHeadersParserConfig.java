package com.agh.technology.xp.project.webscraper.articles.config;


public class ArticleHeadersParserConfig {
    public SectionConfig sectionConfig;
    public HeaderConfig headerConfig;

    public ArticleHeadersParserConfig(SectionConfig sectionConfig, HeaderConfig headerConfig) {
        this.sectionConfig = sectionConfig;
        this.headerConfig = headerConfig;
    }
}
