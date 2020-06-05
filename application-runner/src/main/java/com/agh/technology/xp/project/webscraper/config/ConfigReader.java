package com.agh.technology.xp.project.webscraper.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class ConfigReader {

    public ConfigData readConfigFile() {
        JSONParser parser = new JSONParser();

        String host=null;
        String port=null;

        try {
            Object obj = parser.parse(new FileReader("config.json"));

            JSONObject jsonObject = (JSONObject) obj;

            host = (String) jsonObject.get("host");

            port = (String) jsonObject.get("port");

        } catch (IOException | ParseException e) {
            System.out.println("Nie znaleziono pliku konfiguracyjnego. Zastosowano domyślne ustawienia");
            return new ConfigData("www.interia.pl", "443");
        }
        return new ConfigData(host, port);
    }


}

