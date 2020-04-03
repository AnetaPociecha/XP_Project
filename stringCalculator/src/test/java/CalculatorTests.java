import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class CalculatorTests{

    static StringCalculator calculator = new StringCalculator();

    @RunWith(Parameterized.class)
    public static class AddUpToTwoNumbersWhenStringIsValid {
        @Parameterized.Parameter(0)
        public String calculation;
        @Parameterized.Parameter(1)
        public int expected;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"", 0},
                    {"1", 1},
                    {"1,2", 3}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testAdd() throws Exception {
            assertEquals(calculator.add(this.calculation), this.expected);
        }
    }

    @RunWith(Parameterized.class)
    public static class AnyNumbersWhenStringIsValid {
        @Parameterized.Parameter(0)
        public String calculation;
        @Parameterized.Parameter(1)
        public int expected;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"1,2,3", 6},
                    {"10,90,20,10", 130}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testAdd() throws Exception {
            assertEquals(calculator.add(this.calculation), this.expected);
        }
    }

    @RunWith(Parameterized.class)
    public static class AddCommaOrNewLineAsSeparatorWhenStringIsValid {
        @Parameterized.Parameter(0)
        public String calculation;
        @Parameterized.Parameter(1)
        public int expected;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"1,2\n3", 6},
                    {"10\n90\n20,10", 130}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testAdd() throws Exception {
            assertEquals(calculator.add(this.calculation), this.expected);
        }
    }

    @RunWith(Parameterized.class)
    public static class AddCustomSeparatorWhenStringIsValid {
        @Parameterized.Parameter(0)
        public String calculation;
        @Parameterized.Parameter(1)
        public int expected;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"//;\n1;2;3", 6},
                    {"//;\n10;90;20;10", 130}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testAdd() throws Exception {
            assertEquals(calculator.add(this.calculation), this.expected);
        }
    }

    @RunWith(Parameterized.class)
    public static class CheckNegativeExceptionsSeparatorWhenStringIsInvalid {
        @Parameterized.Parameter(0)
        public String calculation;


        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"//;\n1;-2;3"},
                    {"//;\n10;-90;20;-10"}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testAdd() throws Exception {
            assertThrows(Exception.class,() -> {calculator.add(calculation);});
        }
    }


}