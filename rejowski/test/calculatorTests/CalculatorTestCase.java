package calculatorTests;

import main.calculator.Calculator;
import main.calculator.CalculatorInputFormatException;
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
public class CalculatorTestCase {

    @RunWith(Parameterized.class)
    public static class ParametrizedCalculatorTestCase {


        @Parameter(0)
        public String testData;
        @Parameter(1)
        public int expectedResult;


        private Calculator calculator = new Calculator();

        @Parameters
        public static Collection<Object[]> params() {
            Object[][] data = new Object[][]{
                    {"1,2", 3}, // 0
                    {",", 0}, // 1
                    {"", 0}, // 2 -  First requirement
                    {"1,2,3", 6}, // 3
                    {"1,2,3,4,5,6", 21}, // 4 - Second requirement
                    {"1\n2,3", 6}, // 5 - Third requirement
                    {"//;\n1;2", 3} // 6 - Fourth requirement
            };
            return Arrays.asList(data);
        }



        @Test
        public void testAddsTwoSeparatedNumbers() throws CalculatorInputFormatException {
            assertEquals(this.calculator.addNumbers(this.testData), this.expectedResult);
        }


    }

    @RunWith(Parameterized.class)
    public static class InvalidDelimiterCalculatorTestCase {
        @Parameter()
        public String incorrectDelimiterData;

        @Parameters
        public static Collection<Object[]> params() {
            Object[][] data = new Object[][]{
                    {"1\n2,,3"},
                    {"1\n\n2,,3"}, // Third requirement
                    {"//;\n1;;2"} //Fourth requirement
            };
            return Arrays.asList(data);
        }

        private Calculator calculator =  new Calculator();


        @Test(expected = CalculatorInputFormatException.class)
        public void testFailsOnIncorrectDelimiters() throws CalculatorInputFormatException {
            calculator.addNumbers(incorrectDelimiterData);
        }
    }

    @RunWith(Parameterized.class)
    public static class NegativeInputCalculatorTestCase {
        @Parameter()
        public String negativeInputData;

        @Parameters
        public static Collection<Object[]> params() {
            Object[][] data = new Object[][]{
                    {"1\n2,-3"}, {"//;1\n2,-3;-4\n-5"},
            };
            return Arrays.asList(data);
        }

        private Calculator calculator =  new Calculator();


        @Test(expected = CalculatorInputFormatException.class)
        public void testFailsOnIncorrectDelimiters() throws CalculatorInputFormatException {
            this.calculator.addNumbers(negativeInputData);
        }
    }
}