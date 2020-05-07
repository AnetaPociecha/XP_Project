package parser;

import datamodel.ArticleContainer;
import datamodel.ArticleHeader;
import datamodel.ArticleSection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArticleHeadersParser {
    public static ArticleContainer fetchAndParse(String targetUrl) throws IOException {
        List<String> sectionsToParse = Arrays.asList("special", "facts", "business", "sport", "automotive", "technologies", "tiles");
        Document doc = Jsoup.connect(targetUrl).get();

        Stream<Element> sectionElements = sectionsToParse.stream().map(doc::getElementById);
        List<ArticleSection> sections = sectionElements.map(ArticleHeadersParser::parseSection).collect(Collectors.toList());

        String title = doc.title();
        System.out.println(title);
        return new ArticleContainer(sections);
    }


    private static ArticleSection parseSection(Element sectionElem){
        String name = !sectionElem.select("a.header-a").isEmpty() ? sectionElem.select("a.header-a").first().attr("title") : "extra";
        List<ArticleHeader> headers = sectionElem.select("a.news-a, a.articles-a, a.news-one-a, a.special-a, a.news-gallery-a, a.tiles-a").stream()
                .map(elem -> new ArticleHeader(elem.attr("title"), elem.attr("href")))
                .collect(Collectors.toList());

        return new ArticleSection(name, headers);
    }
}
