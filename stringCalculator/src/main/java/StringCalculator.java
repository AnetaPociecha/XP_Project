import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String numbersList) throws Exception {
        List<String> delimiters = new ArrayList<>(Arrays.asList("\n",","));

        if(numbersList.startsWith("//")){
            delimiters.add(numbersList.split("\n")[0].substring(2));
            numbersList = numbersList.substring(numbersList.indexOf('\n') + 1);
        }

        String delimiterRegex = delimiters.stream().collect(Collectors.joining("|"));

        //Check for negative values
        List<String> negativeNumbers = Arrays.stream(numbersList.split(delimiterRegex))
                .map(string -> string.equals("") ? "0" : string)
                .map(string -> Integer.parseInt(string))
                .filter(number -> number < 0)
                .map(Object::toString)
                .collect(Collectors.toList());
        if(!negativeNumbers.isEmpty()){
            throw new Exception("Negative values " + negativeNumbers.toString());
        }
        return Arrays.stream(numbersList.split(delimiterRegex))
                .map(string -> string.equals("") ? "0" : string)
                .map(string -> Integer.parseInt(string))
                .reduce(0, (accumulator, number) -> accumulator+number);
    }
}
