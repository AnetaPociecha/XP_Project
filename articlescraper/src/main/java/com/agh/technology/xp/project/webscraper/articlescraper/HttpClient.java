package com.agh.technology.xp.project.webscraper.articlescraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HttpClient implements IHttpClient{
     public Document getDocument(String url) throws IOException {
          return Jsoup.connect(url).get();
     }
}
