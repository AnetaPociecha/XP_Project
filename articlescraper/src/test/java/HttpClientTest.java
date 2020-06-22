import com.agh.technology.xp.project.webscraper.articlescraper.HttpClient;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HttpClientTest {

    @Test
    void getDocument() throws IOException {
        HttpClient client = new HttpClient();
        assertDoesNotThrow(() -> client.getDocument("https://www.interia.pl:443"));
        Document document = client.getDocument("https://www.interia.pl:443");
        assertNotNull(document);
    }
}
