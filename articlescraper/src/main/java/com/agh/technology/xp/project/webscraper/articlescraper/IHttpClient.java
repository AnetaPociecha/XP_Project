package com.agh.technology.xp.project.webscraper.articlescraper;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface IHttpClient {
    Document getDocument(String url) throws IOException;
}
