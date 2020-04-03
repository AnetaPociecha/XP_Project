package com.pociecha;

public class BitsCounter {

    public int noOfBits1(String number) {
        int intNumber = Integer.parseInt(number);

        if(intNumber < 0 || intNumber > 255) {
            throw new IllegalArgumentException();
        }

        return Integer.bitCount(intNumber);
    }
}
