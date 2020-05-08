
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class ArticleSectionTest {
    @Test
    public void getAllTitlesInSection() {
        List<ArticleHeader> headers = Arrays.asList(
                new ArticleHeader("title1", "url1"),
                new ArticleHeader("title2", "url2"),
                new ArticleHeader("title3", "url3")
        );

        ArticleSection section = new ArticleSection("example", headers);

        assertEquals(section.getTitles(), Arrays.asList("title1", "title2", "title3"));
    }

}
