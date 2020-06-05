package com.agh.technology.xp.project.webscraper.io;

import java.io.IOException;

public class CLIPrinter implements IPrinter {
    final private String operatingSystem = System.getProperty("os.name");

    public void print(String text) {
        System.out.println(text);
    }

    public void clearScreen() {
        try {
            if (operatingSystem .contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
