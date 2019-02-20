package com.muditasoft.part01._02calculator;

public class Calculator {


    public int add(int number1, int number2) {
        return number1 + number2;
    }

    public int multiply(int number1, int number2) {
        return number1 * number2;
    }

    public int divide(int number1, int number2) {
        if (number2 == 0) {
            throw new ArithmeticException("/ by zero");
        }

        return number1 / number2;
    }

}
