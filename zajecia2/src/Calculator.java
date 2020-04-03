import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Calculator {
    public int add(String numbersInput) {
        String delimiters = "[,\n]";

        Supplier<IntStream> numbers = () -> Arrays.stream(numbersInput.split(delimiters))
                .filter(x -> !x.isEmpty())
                .mapToInt(Integer::parseInt);

        if (numbers.get().anyMatch(x -> x < 0)) {
            throw new IllegalArgumentException("Negatives not allowed");
        }

        return numbers.get().sum();
    }
}
