
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticleHeadersParserTest {
    private final ArticleHeadersParser parser = new ArticleHeadersParser();

    @Test
    public void shouldParseSection() throws IOException {
        File input = new File("src/test/resources/section_mock.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        Element sectionElement = doc.getElementById("facts");



        ArticleSection parsedSection = parser.parseSection(sectionElement);

        assertEquals(parsedSection.getName(), "Fakty");
        assertEquals(parsedSection.getTitles().size(), 20);

        assertEquals(parsedSection.getTitles().get(0), "Szymon Hołownia szykuje się na wybory 23 maja");
    }

    @Test
    public void shouldParseWholeDocument() throws IOException {
        File input = new File("src/test/resources/page_mock.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        ArticleContainer articleContainer = parser.parseDocument(doc);

        assertEquals(articleContainer.getAllSections().size(), 7);
        assertTrue(articleContainer.getAllSections().stream().allMatch(section -> section.getTitles().size() > 0));
        assertTrue(articleContainer.getAllSections().stream().flatMap(section -> section.getArcticleHeaders().stream()).allMatch(header -> header.getUrl().startsWith("https://")));

        assertEquals(articleContainer.getAllTitles().size(), 97);

    }
}
