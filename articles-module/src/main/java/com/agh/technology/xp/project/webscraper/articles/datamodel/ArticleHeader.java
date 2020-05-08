package com.agh.technology.xp.project.webscraper.articles.datamodel;

public class ArticleHeader {
    private final String title;

    private final String url;

    public ArticleHeader(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleHeader that = (ArticleHeader) o;
        return title.equals(that.title) &&
                url.equals(that.url);
    }
}