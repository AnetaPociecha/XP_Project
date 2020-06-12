import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articlescraper.*;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InteriaArticleClientTest {

    @Test
    void getInteriaArticle() throws HttpRequestException, InvalidGetterStrategyException {
        ArticleHeadersParserConfig articleHeadersParserConfig = ArticleHeadersParserConfig.defaultConfig();
        IArticleParser parser = new InteriaArticleParser(articleHeadersParserConfig);
        ArticleDetailsClient validClient = new ArticleDetailsClient(new MockHttpClient(), parser);
        ArticleDetailsClient invalidClient = new ArticleDetailsClient(new ExceptionHttpClient(), parser);

        assertDoesNotThrow(() -> validClient.getInteriaArticle(""));
        assertThrows(HttpRequestException.class, () -> invalidClient.getInteriaArticle(""), "test" );

        IArticle article = validClient.getInteriaArticle("");
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