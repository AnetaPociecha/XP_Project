package com.agh.technology.xp.project.webscraper.io;

public class CLIPrinter implements IPrinter {
    public void print(String text) {
        System.out.println(text);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
