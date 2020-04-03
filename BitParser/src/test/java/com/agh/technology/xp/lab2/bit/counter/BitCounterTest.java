package com.agh.technology.xp.lab2.bit.counter;

import com.agh.technology.xp.lab2.common.library.exception.DebugException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class BitCounterTest {

    private static BitCounter testObject = new BitCounter();

    @RunWith(Parameterized.class)
    public static class ValidArgumentsTest {
        @Parameterized.Parameter()
        public String inlineParameter;
        @Parameterized.Parameter(1)
        public int expectedResult;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resource = new Object[][]{
                    {"1", 1},
                    {"8", 1},
                    {"7", 3},
                    {"1\n8,7,8", 6},
            };
            return Arrays.asList(resource);
        }

        @Test
        public void testBitCounterValid() throws DebugException {
            assertEquals(expectedResult, testObject.noOfBits1(inlineParameter));
        }

    }

    @RunWith(Parameterized.class)
    public static class InvalidArgumentsTest {
        @Parameterized.Parameter()
        public String inlineParameter;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resource = new Object[][]{
                    {"256"},
                    {"-1"}
            };
            return Arrays.asList(resource);
        }

        @Test(expected = RuntimeException.class)
        public void testBitCounterInvalid() throws DebugException {
            testObject.noOfBits1(inlineParameter);
        }
    }

}
