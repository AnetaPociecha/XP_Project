package interiaarticle;

import interiaarticle.model.InteriaArticle;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class InteriaArticleClient {
    public InteriaArticle downloadAndParseArticle(String articleUrl) throws HttpRequestException {
        HttpClient client = new HttpClient();

        try {
            Document articleDocument = client.getDocument(articleUrl);
            InteriaArticleParser parser = new InteriaArticleParser();
            return parser.parse(articleDocument);
        } catch (IOException e) {
            throw new HttpRequestException(e.getMessage());
        }
    }
}
