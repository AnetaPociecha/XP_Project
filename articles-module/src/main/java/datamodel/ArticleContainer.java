package datamodel;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleContainer {

    private final List<ArticleSection> sections;

    public ArticleContainer(List<ArticleSection> sections){
        this.sections = sections;
    }


    public void addSection(ArticleSection section) {
        sections.add(section);
    }

    public List<ArticleSection> getAllSections() {
        return sections;
    }

    public List<String> getAllTitles() {
        return sections.stream().flatMap(section -> section.getTitles().stream()).collect(Collectors.toList());
    }

}
