import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticle;
import com.agh.technology.xp.project.webscraper.articlescraper.InteriaArticleParser;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InteriaArticleParserTest {

    @Test
    void parse() throws IOException {
        Document document = MockDocument.get();
        InteriaArticleParser parser = new InteriaArticleParser();
        InteriaArticle article = parser.parse(document);
        assertEquals("Sekretarz generalny ONZ Antonio Guterres: Pandemia uruchomiła tsunami nienawiści", article.getTitle());
        assertEquals(MockDocument.ARTICLE_CONTENT, article.getContent());
    }


}