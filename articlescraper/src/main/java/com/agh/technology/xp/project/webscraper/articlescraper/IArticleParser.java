package com.agh.technology.xp.project.webscraper.articlescraper;

import org.jsoup.nodes.Document;

public interface IArticleParser {
    IArticle parse(Document document);
}