package com.agh.technology.xp.project.webscraper.io;

import java.util.Scanner;

public class CLIScanner implements IScanner {
    private static final Scanner scanner = new Scanner(System.in);


    @Override
    public Integer scanIntegerFromInput(){
        try{
            String scannedInput = scanner.nextLine();
            return Integer.parseInt(scannedInput);
        } catch (NumberFormatException e){
            System.out.println("Wpisana wartość nie jest liczbą, spróbuj ponownie");
            return scanIntegerFromInput();
        }
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
