package me.asmolikowska;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitsCounterTest {

    @Test
    public void countOneNumberWhenStringIsValid() {
        BitsCounter bitsCounter = new BitsCounter();
        var result = bitsCounter.noOfBits1("6");
        assertEquals(result, 2);
        result = bitsCounter.noOfBits1("0");
        assertEquals(result, 0);
        result = bitsCounter.noOfBits1("255");
        assertEquals(result, 8);
    }

    @Test
    public void countShouldThrowWAnExceptionWhenOutOfRange() {
        BitsCounter bitsCounter = new BitsCounter();

        assertThrows(IllegalArgumentException.class, () -> bitsCounter.noOfBits1("256"));
        assertThrows(IllegalArgumentException.class, () -> bitsCounter.noOfBits1("-1"));
    }


    @Test
    public void countNumbersWhenStringIsValid() {
        BitsCounter bitsCounter = new BitsCounter();

        assertEquals(bitsCounter.noOfBits1("1;1;$1"), 3);

    }

    @Test
    public void countNumbersWhenStringIsNotValid() {
        BitsCounter bitsCounter = new BitsCounter();

        assertThrows(IllegalArgumentException.class, () -> bitsCounter.noOfBits1("123;$ff ' 123"));

    }

}
