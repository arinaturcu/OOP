package com.oop_pub.exceptions.ex2_3;

import java.util.List;

public class MainEx2 {
    public static void main(String[] args) {
        // DONE: Initialize the calculator
        DoubleCalculator calculator = new DoubleCalculator();

        System.out.println(calculator.add(2d, 3d));
        System.out.println(calculator.divide(9d, 4d));
        System.out.println(calculator.average(List.of(1d, 2d, 3d, 4d)));

        // DONE: Test edge cases that would throw exceptions
        try {
            System.out.println(calculator.add(null, 3d));
        } catch (Calculator.NullParameterException | Calculator.OverflowException | Calculator.UnderflowException e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println(calculator.add(Double.POSITIVE_INFINITY, 5d));
        } catch (Calculator.NullParameterException | Calculator.OverflowException | Calculator.UnderflowException e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println(calculator.add(Double.NEGATIVE_INFINITY - 5, -200d));
        } catch (Calculator.NullParameterException | Calculator.OverflowException | Calculator.UnderflowException e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println(calculator.divide(9d, null));
        } catch (Calculator.NullParameterException | Calculator.OverflowException | Calculator.UnderflowException e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println(calculator.average(List.of(Double.POSITIVE_INFINITY - 2, 2d, 3d, 4d)));
        } catch (Calculator.NullParameterException | Calculator.OverflowException | Calculator.UnderflowException e) {
            System.out.println(e.toString());
        }
    }
}
