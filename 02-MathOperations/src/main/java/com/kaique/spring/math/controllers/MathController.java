package com.kaique.spring.math.controllers;

import com.kaique.spring.math.converters.MathConverter;
import com.kaique.spring.math.services.MathServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    private MathServices math = new MathServices();

    @GetMapping("/sum/{firstNumber}/{secondNumber}")
    public Double sum(@PathVariable(value = "firstNumber") String firstNumber,
                      @PathVariable(value = "secondNumber") String secondNumber) throws Exception {

        if (!MathConverter.isNumeric(firstNumber) || !MathConverter.isNumeric(secondNumber)) {
            throw new UnsupportedOperationException("Please set a numerical value.");
        }
        return math.sum(MathConverter.convertToDouble(firstNumber), MathConverter.convertToDouble(secondNumber));
    }

    @GetMapping("/subtraction/{firstNumber}/{secondNumber}")
    public Double subtraction(@PathVariable(value = "firstNumber") String firstNumber,
                              @PathVariable(value = "secondNumber") String secondNumber) throws Exception {

        if (!MathConverter.isNumeric(firstNumber) || !MathConverter.isNumeric(secondNumber)) {
            throw new UnsupportedOperationException("Please set a numerical value.");
        }
        return math.subtraction(MathConverter.convertToDouble(firstNumber), MathConverter.convertToDouble(secondNumber));
    }

    @GetMapping("/multiplication/{firstNumber}/{secondNumber}")
    public Double multiplication(@PathVariable(value = "firstNumber") String firstNumber,
                                 @PathVariable(value = "secondNumber") String secondNumber) throws Exception {

        if (!MathConverter.isNumeric(firstNumber) || !MathConverter.isNumeric(secondNumber)) {
            throw new UnsupportedOperationException("Please set a numerical value.");
        }
        return math.multiplication(MathConverter.convertToDouble(firstNumber), MathConverter.convertToDouble(secondNumber));    }

    @GetMapping("/division/{firstNumber}/{secondNumber}")
    public Double division(@PathVariable(value = "firstNumber") String firstNumber,
                           @PathVariable(value = "secondNumber") String secondNumber) throws Exception {

        if (!MathConverter.isNumeric(firstNumber) || !MathConverter.isNumeric(secondNumber)) {
            throw new UnsupportedOperationException("Please set a numerical value.");
        }
        return math.division(MathConverter.convertToDouble(firstNumber), MathConverter.convertToDouble(secondNumber));    }

    @GetMapping("/average/{firstNumber}/{secondNumber}")
    public Double average(@PathVariable(value = "firstNumber") String firstNumber,
                          @PathVariable(value = "secondNumber") String secondNumber) throws Exception {

        if (!MathConverter.isNumeric(firstNumber) || !MathConverter.isNumeric(secondNumber)) {
            throw new UnsupportedOperationException("Please set a numerical value.");
        }
        return math.average(MathConverter.convertToDouble(firstNumber), MathConverter.convertToDouble(secondNumber));    }

    @GetMapping("/squareRoot/{number}")
    public Double squareRoot(@PathVariable(value = "number") String number) throws Exception {
        if (!MathConverter.isNumeric(number)) {
            throw new UnsupportedOperationException("Please set a numerical value.");
        }
        return math.squareRoot(MathConverter.convertToDouble(number));    }
}