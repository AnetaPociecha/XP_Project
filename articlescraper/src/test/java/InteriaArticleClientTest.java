import com.agh.technology.xp.project.webscraper.articlescraper.*;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InteriaArticleClientTest {

    @Test
    void getInteriaArticleMock() throws HttpRequestException {
        IArticleParser parser = new InteriaArticleParser();
        ArticleDetailsClient validClient = new ArticleDetailsClient(new MockHttpClient(), parser);
        ArticleDetailsClient invalidClient = new ArticleDetailsClient(new ExceptionHttpClient(), parser);

        assertDoesNotThrow(() -> validClient.getInteriaArticle(""));
        assertThrows(HttpRequestException.class, () -> invalidClient.getInteriaArticle(""), "test" );

        IArticle article = validClient.getInteriaArticle("");
        assertNotNull(article.getTitle());
        assertNotNull(article.getContent());
    }

    @Test
    void getInteriaArticleOnline() throws HttpRequestException {
        IArticleParser parser = new InteriaArticleParser();
        ArticleDetailsClient client = new ArticleDetailsClient(new HttpClient(), parser);
        String url = "https://fakty.interia.pl/raporty/raport-wybory-prezydenckie-2020/aktualnosci/news-pozew-w-trybie-wyborczym-przeciwko-rafalowi-trzaskowskiemu-j,nId,4563414";

        assertDoesNotThrow(() -> client.getInteriaArticle(url));
        assertThrows(HttpRequestException.class, () -> client.getInteriaArticle(url+":)"));

        IArticle article = client.getInteriaArticle(url);
        assertNotNull(article.getTitle());
        assertNotNull(article.getContent());
    }

    static class MockHttpClient implements IHttpClient {
        @Override
        public Document getDocument(String url) throws IOException {
            return MockDocument.get();
        }
    }

    static class ExceptionHttpClient implements IHttpClient {
        @Override
        public Document getDocument(String url) throws IOException {
            throw new IOException("test");
        }
    }
}