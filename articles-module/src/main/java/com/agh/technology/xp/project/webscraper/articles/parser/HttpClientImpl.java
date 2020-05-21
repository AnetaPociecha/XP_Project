package com.agh.technology.xp.project.webscraper.articles.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HttpClientImpl implements HttpClient {

    @Override
    public Document fetchDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
