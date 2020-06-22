package com.agh.technology.xp.project.webscraper.articles.config.getterstrategy;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class AttributeGetter {
    private String elementSelector;
    private HTMLGetterStrategy attributeGetterStrategy;

    public AttributeGetter(String elementSelector, HTMLGetterStrategy attributeGetterStrategy) {
        this.elementSelector = elementSelector;
        this.attributeGetterStrategy = attributeGetterStrategy;
    }

    public Element getElement(Element rootElement){
        return rootElement.selectFirst(elementSelector);
    }

    public String getElementAttribute(Element elem){
        Element subElement = elem.selectFirst(elementSelector);
        return attributeGetterStrategy.getAttribute(subElement);
    }

    public Elements getElements(Element rootElement){
        return rootElement.select(elementSelector);
    }

    public List<String> getElementsAttributes(Elements elements){
        return elements.stream().map(attributeGetterStrategy::getAttribute).collect(Collectors.toList());
    }
}
