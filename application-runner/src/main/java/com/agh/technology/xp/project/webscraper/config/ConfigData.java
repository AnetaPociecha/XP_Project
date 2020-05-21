package com.agh.technology.xp.project.webscraper.config;

@SuppressWarnings("unused")
public class ConfigData {

    private String host;
    private String port;

    public ConfigData(String host, String port) {
        this.host = host;
        this.port = port;
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
}
