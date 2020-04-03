package me.asmolikowska;

//          a) implementujemy funkcję o sygnaturze
//        int noOfBits1(String number)
//        (lub analogicznej, jeśli używacie Państwo języka innego niż Java)
//
//        Funkcja jako argument (number) dostaje co najwyżej jedną liczbę 8-bitową nieujemną (w zakresie 0-255) w reprezentacji dziesiętnej i zwraca ilość bitów równych '1' w reprezentacji binarnej tej liczby.
//        Dla pustego napisu funkcja powinna zwracać wartość zero.
//
//        b) Dla liczby poza zakresem (0-255) funkcja powinna zgłaszać wyjątek (błąd).
//
//        c) Dopuszczamy przekazanie więcej niż jednej liczby w ramach napisu (argumentu).
//        Zmieniamy sygnaturę (nazwę argumentu) na:
//        int noOfBits1(String numbers)
//        W ramach napisu (numbers) funkcja powinna dopuszczać dowolną ilość liczb całkowitych (w zakresie 0-255) rozdzielonych średnikami
//
//        d) Funkcja powinna dopuszczać jako znak rozdzielający liczby (w ramach argumentu numbers) zarówno średnik jak i spację
//
//        e) Funkcja powinna dopuszczać jako znak rozdzielający liczby zarówno średnik jak i dowolny ciąg składający się z białych znaków (spacji, tabulacji, znaków końca linii)
//
//        f) Jeżeli lista liczb (argument numbers) ma niepoprawny format (np. używa innego separatora niż określono powyżej), funkcja powinna zgłaszać wyjątek (błąd)
//
//        e) Funkcja powinna dopuszczać w ramach argumentu numbers zarówno liczby w reprezentacji dziesiętnej (np. 255) jak i szesnastkowej – zapisane z prefiksem '$' (np. $ff)
//        Liczby w tych dwóch reprezentacjach mogą być wymieszane np. '10,$a4, $ff, 253'.

import java.util.regex.Pattern;

public class BitsCounter {

    public int noOfBits1(String numbers) {
        Pattern numberPattern = Pattern.compile("(([0-9]+|(\\$[a-zA-Z0-9]+))(\\s+|;))*([0-9]+|(\\$[a-zA-Z0-9]+))");
        if (!numbers.matches(numberPattern.pattern())) {
            throw new IllegalArgumentException("Passed argument does not comply with requirements.");
        }
        String[] splittedNumbers = numbers.split("(\\s+|;)");

        int result = 0;

        for (String element : splittedNumbers) {
            int value;
            if (element.startsWith("$")) {
                value = Integer.parseInt(element.substring(1), 16);
            } else {
                value = Integer.parseInt(element);
            }

            if(value > 255 || value < 0){
                throw new IllegalArgumentException("Value is out of range");
            }

            String binary = Integer.toBinaryString(value);
            for (char bit : binary.toCharArray()) {
                if (bit == '1') {
                    result += 1;
                }
            }
        }

        return result;
    }
}
