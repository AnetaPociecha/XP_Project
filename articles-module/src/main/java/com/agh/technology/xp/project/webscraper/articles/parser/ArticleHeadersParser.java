package com.agh.technology.xp.project.webscraper.articles.parser;

import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import org.jsoup.nodes.Document;

public interface ArticleHeadersParser {

    ArticleContainer parseDocument(Document document);

}
