package com.agh.technology.xp.project.webscraper.articles.datamodel;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleSection {
    private String sectionName;
    private List<ArticleHeader> arcticleHeaders;

    public ArticleSection(String name, List<ArticleHeader> arcticleHeaders) {
        this.sectionName = name;
        this.arcticleHeaders = arcticleHeaders;
    }

    public List<String> getTitles(){
        return arcticleHeaders.stream().map(ArticleHeader::getTitle).collect(Collectors.toList());
    }

    public String getName(){
        return this.sectionName;
    }

    public List<ArticleHeader> getArcticleHeaders() {
        return arcticleHeaders;
    }

    @Override
    public String toString() {
        return "ArticleSection{" +
                "sectionName='" + sectionName + '\'' +
                ", arcticleHeaders=" + arcticleHeaders +
                '}';
    }
}
