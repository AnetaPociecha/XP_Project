package com.agh.technology.lab2string.calculator;

import com.agh.technology.lab2string.calculator.utils.NumbersParser;
import com.agh.technology.xp.lab2.common.library.exception.DebugException;

import java.util.List;

public class StringCalculator {


    public int addNumbers(String numberStr) throws DebugException {
        try {
            if(numberStr.isEmpty()){
                return 0;
            }
            List<Integer> numbers = NumbersParser.splitNumbersString(numberStr);
            return numbers.stream().reduce(0, Integer::sum);
        } catch (Exception e) {
            throw new DebugException(e.getMessage(), e);
        }
    }
}
