package com.agh.technology.xp.project.webscraper.articlescraper;

import java.io.IOException;

public class HttpRequestException extends IOException {
    HttpRequestException(String m){
        super(m);
    }
}
