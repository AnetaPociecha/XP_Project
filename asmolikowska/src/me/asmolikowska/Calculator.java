package me.asmolikowska;

import com.sun.jdi.event.ExceptionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class Calculator {
    public int add(String numbers) throws IllegalArgumentException {
        String[] numbersList = numbers.split("[,\n//;]");

        int result = 0;

        for (String element : numbersList) {
            if (element.equals("")) {
                continue;
            }

            int value = Integer.parseInt(element);
            if (value < 0){
                throw new IllegalArgumentException("Negatives not allowed: " + value);
            }
            result += value;
        }

        return result;
    }
}
