package com.agh.technology.xp.project.webscraper.config;

import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.AttributeGetter;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.GetterType;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.HTMLGetterStrategy;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONOperations {

    public static Object getNestedObjectValue(JSONObject object, String key) {
        return object.get(key);
    }

    public static List<String> getStringListValue(JSONObject object, String key) {
        JSONArray sectionsArray = (JSONArray) object.get(key);

        return new ArrayList<String>(sectionsArray);
    }

    public static AttributeGetter getNestedAttributeGetter(JSONObject object) throws InvalidGetterStrategyException {
        GetterType getterType = GetterType.valueOf((String) object.get("getterType"));
        HTMLGetterStrategy strategy = new HTMLGetterStrategy(getterType, (String) object.get("attributeKey"), (String) object.get("default"));
        return new AttributeGetter((String) object.get("selector"), strategy);
    }


}
