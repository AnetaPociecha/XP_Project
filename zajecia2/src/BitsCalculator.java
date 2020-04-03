import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BitsCalculator {
    public int noOfBits1(String input) {
        Pattern numberPattern = Pattern.compile("(([0-9]+|(\\$[a-fA-F0-9]+))(\\s+|;))*([0-9]+|(\\$[a-fA-F0-9]+))");
        if (!input.matches(numberPattern.pattern())) {
            throw new IllegalArgumentException("Invalid input");
        }

        List<Integer> numbers = Arrays.stream(input.split("\\s+|;"))
            .mapToInt(x -> {
                if (x.startsWith("$")) {
                    return Integer.parseInt(x.replace("$", ""), 16);
                }

                return Integer.parseInt(x);
            }).boxed().collect(Collectors.toList());

        if (numbers.stream().anyMatch(x -> x < 0 || x > 255)) {
            throw new IllegalArgumentException("Value not allowed");
        }

        return numbers
                .stream()
                .mapToInt(Integer::intValue)
                .map(Integer::bitCount)
                .sum();
    }
}
