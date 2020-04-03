package bitparser;

public class BitParserInvalidInputException extends RuntimeException {
    public BitParserInvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}