
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleContainer;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleHeader;
import com.agh.technology.xp.project.webscraper.articles.datamodel.ArticleSection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleContainerTest {

    @Test
    public void shouldGetAllNestedTitles(){
        List<String> mockSectionNames = Arrays.asList("section1","section2","section3");

        List<ArticleHeader> headers = Arrays.asList(
                new ArticleHeader("title1", "url1"),
                new ArticleHeader("title2", "url2"),
                new ArticleHeader("title3", "url3")
        );


        List<ArticleSection> sectionsList = mockSectionNames.stream().map(sectionName -> new ArticleSection(sectionName,headers)).collect(Collectors.toList());
        ArticleContainer container = new ArticleContainer( sectionsList);

        assertEquals(container.getAllTitles(), Arrays.asList("title1", "title2","title3", "title1", "title2", "title3", "title1", "title2", "title3"));
    }
}
