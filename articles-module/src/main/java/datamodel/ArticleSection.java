package datamodel;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleSection {
    private String sectionName;
    private List<ArticleHeader> arcticleHeaders;

    public ArticleSection(String name, List<ArticleHeader> arcticleHeaders) {
        this.sectionName = name;
        this.arcticleHeaders = arcticleHeaders;
    }

    public void addHeader(ArticleHeader header){
        this.arcticleHeaders.add(header);
    }
    public String getName(){
        return this.sectionName;
    }
    public List<String> getTitles(){
        return arcticleHeaders.stream().map(ArticleHeader::getTitle).collect(Collectors.toList());
    }
}
