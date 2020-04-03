package main.bitparser;

import java.util.ArrayList;
import java.util.Arrays;

public class BitParser {


    public int noOfBits1(String numbers) throws BitParserInvalidInputException {
        String[] splitNumbersArr = numbers.replaceAll("[\\s;\n]+", ",").split(",");
        ArrayList<String> splitNumbers = new ArrayList<>(Arrays.asList(splitNumbersArr));
        if (splitNumbers.get(0).isBlank() && splitNumbers.size() == 1)
            return 0;
        return splitNumbers
                .stream()
                .map(this::parseAndValidateRange)
                .map(Integer::bitCount)
                .reduce(0, Integer::sum);
    }

    public int parseAndValidateRange(String stringInteger) throws BitParserInvalidInputException {
        int parsedInteger;
        if (stringInteger.matches("^\\$[\\dabcdefABCDEF]+")) {
            stringInteger = stringInteger.replace("$", "");
            parsedInteger = Integer.parseInt(stringInteger, 16);
        } else {
            parsedInteger = Integer.parseInt(stringInteger);
        }

        if (parsedInteger < 0 || parsedInteger > 255)
            throw new BitParserInvalidInputException("Number must be between 0 and 255");
        return parsedInteger;
    }
}
