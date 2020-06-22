import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.parser.ArticleHeadersParserImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.HttpClientImpl;
import com.agh.technology.xp.project.webscraper.articles.parser.InteriaArticlesListClient;
import com.agh.technology.xp.project.webscraper.articlescraper.*;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArticleDetailsClientTest {

    @Test
    void getInteriaArticleMock() throws HttpRequestException, InvalidGetterStrategyException {
        IArticleParser parser = new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig());

        ArticleDetailsClient validClient = new ArticleDetailsClient(new MockHttpClient(), parser);
        ArticleDetailsClient invalidClient = new ArticleDetailsClient(new ExceptionHttpClient(), parser);

        assertDoesNotThrow(() -> validClient.getInteriaArticle(""));
        assertThrows(HttpRequestException.class, () -> invalidClient.getInteriaArticle(""), "test");

        IArticle article = validClient.getInteriaArticle("");
        assertNotNull(article.getTitle());
        assertNotNull(article.getContent());
    }

    @Test
    void getOneInteriaArticleOnline() throws HttpRequestException, InvalidGetterStrategyException {
        IArticleParser parser = new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig());
        ArticleDetailsClient client = new ArticleDetailsClient(new HttpClient(), parser);
        String url = "https://fakty.interia.pl/raporty/raport-wybory-prezydenckie-2020/aktualnosci/news-pozew-w-trybie-wyborczym-przeciwko-rafalowi-trzaskowskiemu-j,nId,4563414";

        assertDoesNotThrow(() -> client.getInteriaArticle(url));
        assertThrows(HttpRequestException.class, () -> client.getInteriaArticle(url + ":)"));

        IArticle article = client.getInteriaArticle(url);
        assertNotNull(article.getTitle());
        assertFalse(article.getTitle().isEmpty());
        assertNotNull(article.getContent());
        assertFalse(article.getContent().isEmpty());
    }


    @Test
    void getAllInteriaArticleOnline() throws IOException, InvalidGetterStrategyException, HttpRequestException {

        String url = "https://www.interia.pl:443";
        InteriaArticlesListClient interiaArticlesListClient = new InteriaArticlesListClient.InteriaArticlesListClientBuilder()
                .httpClient(new HttpClientImpl())
                .articleHeadersParser(new ArticleHeadersParserImpl(url, ArticleHeadersParserConfig.defaultConfig()))
                .targetUrl(url)
                .build();

        assertDoesNotThrow(interiaArticlesListClient::fetchAndParse);

        ArticleDetailsClient articleDetailsClient
                = new ArticleDetailsClient(new HttpClient(), new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig()));

        ArticleContainer container = interiaArticlesListClient.fetchAndParse();

        container.getAllSections().forEach(section -> {
            section.getArcticleHeaders().forEach(articleHeader -> {
                String articleUrl = articleHeader.getUrl();

                assertDoesNotThrow(() -> articleDetailsClient.getInteriaArticle(articleUrl));

                IArticle article = null;
                try {
                    article = articleDetailsClient.getInteriaArticle(articleUrl);
                    assertNotNull(article.getTitle());
                    //assertFalse(article.getTitle().isEmpty());
                    assertNotNull(article.getContent());
                    assertFalse(article.getContent().isEmpty());

                } catch (HttpRequestException e) {
                    e.printStackTrace();
                }

            });
        });
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