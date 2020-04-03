package com.agh.technology.lab2string.calculator.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumbersParser {

    private static final String SEPARATORS = "[,\\n]";

    public static List<Integer> splitNumbersString(String numberStr) {
        List<String> split = Arrays.asList(numberStr.split(SEPARATORS));
        return split.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
