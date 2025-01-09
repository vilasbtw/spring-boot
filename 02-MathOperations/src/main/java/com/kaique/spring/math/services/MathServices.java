package com.kaique.spring.math.services;

public class MathServices {

    public Double sum(Double firstNumber, Double secondNumber) {
        return firstNumber + secondNumber;
    }

    public Double subtraction(Double firstNumber, Double secondNumber) {
        return firstNumber - secondNumber;
    }

    public Double multiplication(Double firstNumber, Double secondNumber) {
        return firstNumber * secondNumber;
    }

    public Double division(Double firstNumber, Double secondNumber) {
        return firstNumber / secondNumber;
    }

    public Double average(Double firstNumber, Double secondNumber) {
        return (firstNumber + secondNumber)/2;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }
}