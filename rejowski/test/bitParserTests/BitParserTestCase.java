package bitParserTests;

import main.bitparser.BitParser;
import main.bitparser.BitParserInvalidInputException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class BitParserTestCase {
    private static BitParser bitParser = new BitParser();

    @RunWith(Parameterized.class)
    public static class ParametrizedCalculatorTestCase {


        @Parameter(0)
        public String testData;
        @Parameter(1)
        public int expectedResult;


        @Parameters
        public static Collection<Object[]> params() {
            Object[][] data = new Object[][]{
                    {"", 0}, // 0
                    {"1", 1}, // 0
                    {"23", 4}, // 1
                    {"35", 3}, // 2
                    {"64", 1}, // 3
                    {"64;35;23", 8}, // 4
                    {"64;125;27", 11}, // 5
                    {"64;125 27", 11}, // 6
                    {"64 35;23", 8}, // 7
                    {"64 \n35;\t\t\t23", 8}, // 8
                    {"64   \n\t$23;  \n\t$17", 8}, // 9
                    {"64;$7D 27", 11}, // 10
            };
            return Arrays.asList(data);
        }


        @Test
        public void calculatesBitsProperly() throws BitParserInvalidInputException {
            assertEquals(bitParser.noOfBits1(testData), expectedResult);
        }


    }

    @RunWith(Parameterized.class)
    public static class InvalidInputBitParserTestCase {
        @Parameter()
        public String incorrectData;

        @Parameters
        public static Collection<Object[]> params() {
            Object[][] data = new Object[][]{
                    {"-10"},
                    {"256"},
                    {"10000"}
            };
            return Arrays.asList(data);
        }


        @Test(expected = BitParserInvalidInputException.class)
        public void testFailsOnIncorrectDelimiters() throws BitParserInvalidInputException {
            bitParser.noOfBits1(incorrectData);
        }
    }
}