import com.agh.technology.xp.project.webscraper.articlescraper.HttpRequestException;
import com.agh.technology.xp.project.webscraper.articlescraper.IHttpClient;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticle;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleDetailsClient;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InteriaArticleClientTest {

    @Test
    void getInteriaArticle() throws HttpRequestException {
        InteriaArticleDetailsClient validClient = new InteriaArticleDetailsClient(new MockHttpClient());
        InteriaArticleDetailsClient invalidClient = new InteriaArticleDetailsClient(new ExceptionHttpClient());

        assertDoesNotThrow(() -> validClient.getInteriaArticle(""));
        assertThrows(HttpRequestException.class, () -> invalidClient.getInteriaArticle(""), "test" );

        InteriaArticle article = validClient.getInteriaArticle("");
        assertNotNull(article.getTitle());
        assertNotNull(article.getContent());
    }

    class MockHttpClient implements IHttpClient {
        @Override
        public Document getDocument(String url) throws IOException {
            return MockDocument.get();
        }
    }

    class ExceptionHttpClient implements IHttpClient {
        @Override
        public Document getDocument(String url) throws IOException {
            throw new IOException("test");
        }
    }
}