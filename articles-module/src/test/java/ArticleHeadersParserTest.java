
import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleHeadersParserTest {
    private final ArticleHeadersParserImpl parser = new ArticleHeadersParserImpl("", ArticleHeadersParserConfig.defaultConfig());

    ArticleHeadersParserTest() throws InvalidGetterStrategyException {
    }

    @Test
    void shouldParseSection() throws IOException {
        File input = new File("src/test/resources/section_mock.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        Element sectionElement = doc.getElementById("facts");



        ArticleSection parsedSection = parser.parseSection(sectionElement);

        assertEquals(parsedSection.getName(), "Fakty");
        assertEquals(parsedSection.getTitles().size(), 20);

        assertEquals(parsedSection.getTitles().get(0), "Szymon Hołownia szykuje się na wybory 23 maja");
    }

    @Test
    void shouldParseWholeDocument() throws IOException {
        File input = new File("src/test/resources/page_mock.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        ArticleContainer articleContainer = parser.parseDocument(doc);

        assertEquals(articleContainer.getAllSections().size(), 6);
        assertTrue(articleContainer.getAllSections().stream().allMatch(section -> section.getTitles().size() > 0));
        assertTrue(articleContainer.getAllSections().stream().flatMap(section -> section.getArcticleHeaders().stream()).allMatch(header -> header.getUrl().startsWith("https://")));

        assertEquals(articleContainer.getAllTitles().size(), 90);

    }
}
