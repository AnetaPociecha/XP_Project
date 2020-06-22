
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClient;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HttpClientTest {

    @Test
    void getDocument() throws IOException {
        HttpClient client = new HttpClientImpl();
        assertDoesNotThrow(() -> client.fetchDocument("https://www.interia.pl:443"));
        Document document = client.fetchDocument("https://www.interia.pl:443");
        assertNotNull(document);
    }
}
