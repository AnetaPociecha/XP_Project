package com.pociecha;

public class Calculator {
    public int add(String numbers) throws IllegalArgumentException {

        String[] strNumbers = numbers.split("[,\n//;]");

        int sum = 0;

        for (String strValue : strNumbers) {

            int value = Integer.parseInt(strValue);
            if (value < 0){
                throw new IllegalArgumentException("Negatives not allowed: " + value);
            }
            sum += value;
        }

        return sum;
    }
}
