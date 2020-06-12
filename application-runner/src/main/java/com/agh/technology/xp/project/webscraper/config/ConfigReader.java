package com.agh.technology.xp.project.webscraper.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class ConfigReader {

    public ConfigData readConfigFile() {
        JSONParser parser = new JSONParser();

        String host;
        String port;
        List<String> sections;

        try {
            Object obj = parser.parse(new FileReader("config.json"));

            JSONObject jsonObject = (JSONObject) obj;

            host = (String) jsonObject.get("host");

            port = (String) jsonObject.get("port");

            sections = getSections(jsonObject.get("sections"));


        } catch (IOException | ParseException e) {
            System.out.println("Nie znaleziono pliku konfiguracyjnego. Zastosowano domyślne ustawienia");
            sections = Arrays.asList("facts", "business", "sport", "automotive", "technologies", "tiles");
            return new ConfigData("www.interia.pl", "443", sections);
        }
        return new ConfigData(host, port, sections);
    }

    private List<String> getSections(Object jsonSections){
        JSONArray sectionsArray = (JSONArray)jsonSections;

        return new ArrayList<String>(sectionsArray);
    }
}

