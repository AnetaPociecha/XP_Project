package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {

    private ArrayList<String> delimeters = new ArrayList<>(List.of(",", "\n"));

    public int addNumbers(String numbersList) throws CalculatorInputFormatException {
        String preprocessedList = stripRegexOnSplitValues(preprocessString(numbersList));
        if (preprocessedList.isEmpty())
            return 0;
        if (Pattern.compile(getInvalidFormatRegex()).matcher(preprocessedList).find())
            throw new CalculatorInputFormatException("Input contains some invalid formatting");

        List<String> negatives = streamInts(preprocessedList)
                .filter((elem) -> elem < 0)
                .map(Object::toString)
                .collect(Collectors.toList());
        if (negatives.size() > 0)
            throw new CalculatorInputFormatException("Negatives not allowed!" + String.join(", ", negatives));

        return streamInts(preprocessedList).reduce(0, Integer::sum);
    }

    private Stream<Integer> streamInts(String numbersList) {
        return Arrays.stream(numbersList.split(getSplitRegex())).map(Integer::parseInt);
    }

    private String preprocessString(String input) {
        String newDelimeterRegex = "^//(.)";
        Matcher matcher = Pattern.compile(newDelimeterRegex).matcher(input);
        if (matcher.find()) {
            String newDelimeter = matcher.group(1);
            delimeters.add(newDelimeter);
        } else {
            return input;
        }
        return input.split(newDelimeterRegex)[1];
    }

    private String getSplitRegex() {
        return String.format("[%s]", String.join("", delimeters));
    }

    private String getInvalidFormatRegex() {
        return String.format("%s{2,}", getSplitRegex());
    }

    private String stripRegexOnSplitValues(String input) {
        String frontTrimmed = input.replaceAll(String.format("^%s*", getSplitRegex()), "");
        return frontTrimmed.replaceAll(String.format("%s*$", getSplitRegex()), "");
    }

}
