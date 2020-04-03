package com.agh.technology.lab2string.calculator;
import com.agh.technology.xp.lab2.common.library.exception.DebugException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


public class StringCalculatorTest {

    private static StringCalculator testObject = new StringCalculator();


    @RunWith(Parameterized.class)
    public static class ValidArgumentsTest {
        @Parameterized.Parameter()
        public String inlineParameter;
        @Parameterized.Parameter(1)
        public int expectedResult;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resource = new Object[][]{
                    {"", 0},
                    {",", 0},
                    {"1,2,2,3", 8},
                    {"1,2,3\n-10", -4},
            };
            return Arrays.asList(resource);
        }

        @Test
        public void testAddNumbers() throws DebugException {
            assertEquals(testObject.addNumbers(this.inlineParameter), this.expectedResult);
        }

    }


        @RunWith(Parameterized.class)
        public static class InValidArgumentsTest{
            @Parameterized.Parameter()
            public String inlineParameter;
            @Parameterized.Parameters
            public static Collection<Object[]> resource() {
                Object[][] resource = new Object[][]{
                        {"21\n22,,31"},
                };
                return Arrays.asList(resource);
            }
            @Test(expected = DebugException.class)
            public void testInvalidDataNumbers() throws DebugException {
                testObject.addNumbers(inlineParameter);
            }
        }

    }
