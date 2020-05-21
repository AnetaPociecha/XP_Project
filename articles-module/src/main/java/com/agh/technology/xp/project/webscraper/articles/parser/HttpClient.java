package com.agh.technology.xp.project.webscraper.articles.parser;

import org.jsoup.nodes.Document;
import java.io.IOException;

public interface HttpClient {

    Document fetchDocument(String url) throws IOException;

}
