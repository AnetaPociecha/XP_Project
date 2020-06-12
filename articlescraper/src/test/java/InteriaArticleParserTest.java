import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticle;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleParser;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InteriaArticleParserTest {

    @Test
    void parse() throws IOException, InvalidGetterStrategyException {
        Document document = MockDocument.get();
        InteriaArticleParser parser = new InteriaArticleParser(ArticleHeadersParserConfig.defaultConfig());
        InteriaArticle article = parser.parse(document);
        assertEquals(MockDocument.TITLE, article.getTitle());
        assertEquals(MockDocument.ARTICLE_CONTENT, article.getContent());
    }


}