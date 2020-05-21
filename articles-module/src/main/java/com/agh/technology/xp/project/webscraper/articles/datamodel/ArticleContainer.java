package com.agh.technology.xp.project.webscraper.articles.datamodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleContainer {

    private final List<ArticleSection> sections;

    public ArticleContainer(List<ArticleSection> sections){
        this.sections = sections;
    }

    public List<ArticleSection> getAllSections() {
        return sections;
    }

    public List<String> getAllTitles() {
        return sections != null ?
                sections.stream().flatMap(section -> section.getTitles().stream()).collect(Collectors.toList())
                : Collections.emptyList();
    }

    @Override
    public String toString() {
        return "ArticleContainer{" +
                "sections=" + sections +
                '}';
    }
}
