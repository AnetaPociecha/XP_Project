import com.agh.technology.xp.project.webscraper.articlescraper.HttpClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HttpClientTest {

    @Test
    void getDocument() {
        assertDoesNotThrow(() -> HttpClient.class.getMethod("getDocument", String.class));
    }
}
