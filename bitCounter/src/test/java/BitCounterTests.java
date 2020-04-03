import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class BitCounterTests{

    static BitCounter bitCounter = new BitCounter();

    @RunWith(Parameterized.class)
    public static class CountBitsValidInput {
        @Parameterized.Parameter(0)
        public String calculation;
        @Parameterized.Parameter(1)
        public int expected;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"", 0},
                    {"1", 1},
                    {"7", 3},
                    {"7;1;1;7", 8},
                    {"7 1;1 7", 8},
                    {"7;1   \t1\n\t\n 7", 8},
                    {"$3f",6},
                    {"$3f  6 $a",10}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testValidInput() throws NumberOutOfRangeException, WrongFormatException {
            assertEquals(this.expected, bitCounter.noOfBits1(this.calculation));
        }
    }

    @RunWith(Parameterized.class)
    public static class CountBitsInvalidInput {
        @Parameterized.Parameter(0)
        public String calculation;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"-1"},
                    {"278"},
                    {"$aaa"},
                    {"$-f1"}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testValidInput() throws NumberOutOfRangeException {
            assertThrows(NumberOutOfRangeException.class, () -> {bitCounter.noOfBits1(calculation);});
        }
    }

    @RunWith(Parameterized.class)
    public static class CountBitsInvalidFormatInput {
        @Parameterized.Parameter(0)
        public String calculation;

        @Parameterized.Parameters
        public static Collection<Object[]> resource() {
            Object[][] resources = new Object[][]{
                    {"255,24  $52"},
                    {"12\t\n 43,7"}
            };
            return Arrays.asList(resources);
        }

        @Test
        public void testValidInput(){
            assertThrows(WrongFormatException.class, () -> {bitCounter.noOfBits1(calculation);});
        }
    }
}
