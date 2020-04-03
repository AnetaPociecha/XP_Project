import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {
    @Test
    void AddsUpToTwoNumberWhenStringIsValid() {
        Calculator calc = new Calculator();

        assertEquals(calc.add("1,2"), 3);
        assertEquals(calc.add("1"), 1);
        assertEquals(calc.add(""), 0);
    }

    @Test
    public void AddsNumbersUsingNewLineDelimiterWhenStringIsValid() {
        Calculator calc = new Calculator();

        assertEquals(calc.add("1\n2,3"), 6);
        assertEquals(calc.add("10\n90,10\n20"), 130);
    }
}
