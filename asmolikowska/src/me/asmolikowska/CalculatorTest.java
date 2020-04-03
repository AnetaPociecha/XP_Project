package me.asmolikowska;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void addsUpToTwoIntegersWhenStringIsValid() {
        Calculator calc = new Calculator();

        var result = calc.add("1,2");
        assertEquals(result, 3);

        result = calc.add("1");
        assertEquals(result, 1);

        result = calc.add("");
        assertEquals(result, 0);

    }

    @Test
    public void addNumbersUsingNewLineDelimiterWhenStringIsValid() {
        Calculator calc = new Calculator();

        var result = calc.add("1\n2,3");
        assertEquals(result, 6);

        result = calc.add("10\n90,10\n20");
        assertEquals(result, 130);

    }

    @Test
    public void addNumbersUsingCustomDelimiterWhenStringIsValid() {
        Calculator calc = new Calculator();

        var result = calc.add("//;\n1;2");
        assertEquals(result, 3);

        result = calc.add("//;\n1;2;4");
        assertEquals(result, 7);
    }

    @Test
    public void addShouldThrowWAnExceptionWhenNegativeNumbersAreUsed() {
        Calculator calc = new Calculator();

        assertThrows(IllegalArgumentException.class, () -> calc.add("1,-2"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("-2"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("-2,-4"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("-2,4"));
    }
}