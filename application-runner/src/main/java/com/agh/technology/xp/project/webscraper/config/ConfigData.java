package com.agh.technology.xp.project.webscraper.config;

import java.util.List;

@SuppressWarnings("unused")
public class ConfigData {

    private String host;
    private String port;
    private List<String> sections;

    public ConfigData(String host, String port, List<String> sections) {
        this.host = host;
        this.port = port;
        this.sections = sections;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String createUrl(){
        return "https://" + getHost() + ":" + getPort();
    }

    public List<String> getSections(){
        return this.sections;
    }
}
