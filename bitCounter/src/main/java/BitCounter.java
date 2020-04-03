import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BitCounter {

    public int noOfBits1(String numbers) throws NumberOutOfRangeException, WrongFormatException{
        if(numbers.equals("")){
            return 0;
        }
        List<String> delimiters = new ArrayList<>(Arrays.asList(";","\\s+"));

        String delimiterRegex = delimiters.stream().collect(Collectors.joining("|"));

        //CheckFormat
        boolean isFormatCorrect = numbers.replaceAll(delimiterRegex, "")
                .replaceAll(Pattern.quote("$"), "")
                .replaceAll("[0-9]","")
                .replaceAll("[a-f]", "")
                .replaceAll("-","")
                .equals("");

        if(!isFormatCorrect){
            throw new WrongFormatException();
        }

        //Check for numbers out of range
        if(Arrays.stream(numbers.split(delimiterRegex))
                .map(string -> parseInt(string))
                .anyMatch(number -> number < 0 || number > 255)
        ){
            throw new NumberOutOfRangeException("");
        }

        return Arrays.stream(numbers.split(delimiterRegex))
                .map(this::noOfBits1SingleNumber)
                .reduce(0, (accumulator, number) -> accumulator+number);
    }

    private int noOfBits1SingleNumber(String number){
        if(number.equals("")){
            return 0;
        }
        int intNumber = parseInt(number);
        return Integer.bitCount(intNumber);

    }

    private int parseInt(String number){
        if(number.startsWith("$")){
            return Integer.parseInt(number.substring(1),16);
        }
        return Integer.parseInt(number);
    }
}
