package com.agh.technology.xp.project.webscraper.articlescraper;

public class InteriaArticle {

    private String title;
    private String content;

    public InteriaArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
