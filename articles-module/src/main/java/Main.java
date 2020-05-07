import datamodel.ArticleContainer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parser.ArticleHeadersParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArticleContainer container = ArticleHeadersParser.fetchAndParse("https://www.interia.pl/");
    }
}
