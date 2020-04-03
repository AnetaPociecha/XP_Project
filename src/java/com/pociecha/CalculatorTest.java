package com.pociecha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void addsUpToTwoIntegers() {
        Calculator calc = new Calculator();

        var result = calc.add("1,2");
        assertEquals(result, 3);

        result = calc.add("1");
        assertEquals(result, 1);
    }

    @Test
    public void addShouldThrowWAnExceptionWhenNegativeNumbersAreUsed() {
        Calculator calc = new Calculator();

        assertThrows(IllegalArgumentException.class, () -> calc.add("1,-2"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("-2"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("-1,-2"));
    }
}