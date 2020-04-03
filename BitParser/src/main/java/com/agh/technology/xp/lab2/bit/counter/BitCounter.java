package com.agh.technology.xp.lab2.bit.counter;

import com.agh.technology.xp.lab2.bit.counter.utils.BitParser;
import com.agh.technology.xp.lab2.common.library.exception.DebugException;
import java.util.List;

public class BitCounter {


    private final int MIN = 0;
    private final int MAX = 64;

    private final String FILTER = "^\\$[\\dabcdefABCDEF]+";

    public int noOfBits1(String numbers) throws DebugException {

        List<String> numbersList = BitParser.parseNumbers(numbers);

      return numbersList.stream()
                .map(str->{
                    int valid = 0;
                    try {
                        valid = validateStringNumberAndReturnInteger(str);
                    } catch (DebugException ignored) {
                        throw new RuntimeException();
                    }
                    return valid;
                })
                .map(this::computerHammingWeight)
                .reduce(0, Integer::sum);

    }

    private int computerHammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            if (((n >>> i) & 1) == 1) {
                ++count;
            }
        }
        return count;
    }

    private int validateStringNumberAndReturnInteger(String strNumber) throws DebugException {
        int validIntegerNumber;
        if (strNumber.matches(FILTER)) {
            strNumber = strNumber.replace("$", "");
            validIntegerNumber = Integer.parseInt(strNumber, 16);
        } else {
            validIntegerNumber = Integer.parseInt(strNumber);
        }
        if(validIntegerNumber < MIN || validIntegerNumber >= MAX){
            throw new DebugException("Invalid input");
        }

        return validIntegerNumber;
    }


    public static void main(String[] args) throws DebugException {
        BitCounter bitCounter = new BitCounter();
        System.out.println(bitCounter.noOfBits1("7,1,1,7"));
    }
}
