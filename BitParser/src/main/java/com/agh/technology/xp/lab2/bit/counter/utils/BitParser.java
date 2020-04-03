package com.agh.technology.xp.lab2.bit.counter.utils;

import java.util.Arrays;
import java.util.List;

public class BitParser {

    private static  final String SEPARATOR = ",";
    private static final String FILTER = "[\\s;\n]+";

    public static List<String> parseNumbers(String numbers){
        return Arrays.asList(numbers.replaceAll(FILTER, SEPARATOR).split(SEPARATOR));
    }
}
