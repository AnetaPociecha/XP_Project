package com.agh.technology.xp.project.webscraper.config;

import com.agh.technology.xp.project.webscraper.articles.config.ArticleHeadersParserConfig;
import com.agh.technology.xp.project.webscraper.articles.config.HeaderConfig;
import com.agh.technology.xp.project.webscraper.articles.config.SectionConfig;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.AttributeGetter;
import com.agh.technology.xp.project.webscraper.articles.config.getterstrategy.InvalidGetterStrategyException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public class ConfigData {

    public JSONObject parsedConfig;

    private ConfigData(JSONObject jsonObject) {
        this.parsedConfig = jsonObject;
    }


    public static ConfigData fromFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) obj;
        return new ConfigData(jsonObject);
    }

    public ArticleHeadersParserConfig getHeadersParserConfig() throws InvalidGetterStrategyException {
        JSONObject sectionConfigJSON = (JSONObject) JSONOperations.getNestedObjectValue(parsedConfig, "sectionConfig");

        List<String> sectionsSelectors = JSONOperations.getStringListValue(sectionConfigJSON, "selectors");
        AttributeGetter sectionNameGetterConfig = JSONOperations.getNestedAttributeGetter((JSONObject) JSONOperations.getNestedObjectValue(sectionConfigJSON, "name"));

        JSONObject articleHeaderConfigJSON = (JSONObject) JSONOperations.getNestedObjectValue(parsedConfig, "articleConfig");
        String urlSelector = (String) articleHeaderConfigJSON.get("urlSelector");
        AttributeGetter articleTitleGetterConfig = JSONOperations.getNestedAttributeGetter((JSONObject) JSONOperations.getNestedObjectValue(articleHeaderConfigJSON, "title"));

        SectionConfig sectionConfig = new SectionConfig(sectionsSelectors, sectionNameGetterConfig);
        HeaderConfig headerConfig = new HeaderConfig(urlSelector, articleTitleGetterConfig);
        return new ArticleHeadersParserConfig(sectionConfig, headerConfig);

    }

    public String getURL(){
        String host = (String) parsedConfig.get("host");
        String port = (String) parsedConfig.get("port");
        return "https://" + host + ':' + port;

    }


}
