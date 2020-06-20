import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InteriaArticlesListClientTest {

    @Test
    void fetchAndParseValidURL() throws IOException {

        String url = "https://www.interia.pl:443";
        InteriaArticlesListClient client = new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                .httpClient(new HttpClientImpl())
                .articleHeadersParser(new ArticleHeadersParserImpl(url))
                .targetUrl(url)
                .build();

        assertDoesNotThrow(client::fetchAndParse);

        ArticleContainer container = client.fetchAndParse();

        assertNotNull(container.getAllSections());
        assertFalse(container.getAllSections().isEmpty());
        container.getAllSections().forEach(section -> {
            assertNotNull(section);
            assertNotNull(section.getName());
            assertFalse(section.getName().isEmpty());
        });

        assertNotNull(container.getAllTitles());
        assertFalse(container.getAllTitles().isEmpty());
        container.getAllTitles().forEach(title -> {
            assertNotNull(title);
            assertFalse(title.isEmpty());
        });
    }

    @Test
    void fetchAndParseInvalidURL() {

        String url = "https://www.interiaaaa.pl:443";
        InteriaArticlesListClient client = new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                .httpClient(new HttpClientImpl())
                .articleHeadersParser(new ArticleHeadersParserImpl(url))
                .targetUrl(url)
                .build();

        assertThrows(IOException.class, client::fetchAndParse);
    }
}