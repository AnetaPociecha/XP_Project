package com.agh.technology.xp.project.webscraper.articles.config;

import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.AttributeGetter;

import java.util.List;

public class SectionConfig {
    public final List<String> sectionsSelectors;

    public final AttributeGetter sectionNameGetterConfig;


    public SectionConfig(List<String> sectionsSelectors, AttributeGetter sectionNameGetterConfig) {
        this.sectionsSelectors = sectionsSelectors;
        this.sectionNameGetterConfig = sectionNameGetterConfig;
    }
}
