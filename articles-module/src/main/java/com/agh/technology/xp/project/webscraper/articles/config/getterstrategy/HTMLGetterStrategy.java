package com.agh.technology.xp.project.webscraper.articles.config.getterstrategy;

import org.jsoup.nodes.Element;

public class HTMLGetterStrategy {
    private final GetterType getterType;
    private final String attributeKey;
    private final String defaultValue;


    public HTMLGetterStrategy(GetterType getterType, String attributeKey, String defaultValue) throws InvalidGetterStrategyException {
        this.getterType = getterType;
        this.defaultValue = defaultValue;
        if (getterType.equals(GetterType.ATTRIBUTE)) {
            if (attributeKey == null || attributeKey.equals("")) {
                throw new InvalidGetterStrategyException("Cannot instantiate attribute getter strategy without attribute key");
            }
            this.attributeKey = attributeKey;

        } else {
            this.attributeKey = null;
        }

    }

    public String getAttribute(Element elem) {
        switch (getterType) {
            case ATTRIBUTE:
                return getAttributeWithKey(elem);
            case CONTENT:
                return elem.text();
            default:
                return defaultValue;
        }

    }

    private String getAttributeWithKey(Element elem) {
        try {
            return elem.attr(attributeKey);
        } catch (NullPointerException npe) {
            return defaultValue;
        }
    }
}
