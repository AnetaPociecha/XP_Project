import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitsCalculatorTest {

    private final BitsCalculator bitsCalculator = new BitsCalculator();

    @Test
    public void countOneNumberWhenStringIsValid() {
        assertEquals(bitsCalculator.noOfBits1("6"), 2);
        assertEquals(bitsCalculator.noOfBits1("0"), 0);
        assertEquals(bitsCalculator.noOfBits1("255"), 8);
    }

    @Test
    public void shouldThrowAnExceptionWhenOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> bitsCalculator.noOfBits1("256"));
        assertThrows(IllegalArgumentException.class, () -> bitsCalculator.noOfBits1("-1"));
    }


    @Test
    public void countNumbersWhenStringIsValid() {
        assertEquals(bitsCalculator.noOfBits1("15;154   $af"), 14);

    }

    @Test
    public void shouldThrowAnExceptionWhenStringIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> bitsCalculator.noOfBits1("432;$f54a fdsfds"));

    }
}
