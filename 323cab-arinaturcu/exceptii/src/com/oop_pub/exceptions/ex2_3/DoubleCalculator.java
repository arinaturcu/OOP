package com.oop_pub.exceptions.ex2_3;

import java.util.Collection;

public class DoubleCalculator implements Calculator {
    @Override
    public Double add(Double nr1, Double nr2) {
        if (nr1 == null || nr2 == null) {
            throw new NullParameterException();
        }

        double result = nr1 + nr2;

        if (result == Double.POSITIVE_INFINITY) {
            throw new OverflowException();
        }
        if (result == Double.NEGATIVE_INFINITY) {
            throw new UnderflowException();
        }

        return result;
    }

    @Override
    public Double divide(Double nr1, Double nr2) {
        if (nr1 == null || nr2 == null) {
            throw new NullParameterException();
        }

        return nr1/nr2;
    }

    @Override
    public Double average(Collection<Double> numbers) {
        Double sum = 0d;
        int elementsNumber = numbers.size();

        for (Double number : numbers) {
            sum = add(sum, number);
        }

        return divide(sum, (double) elementsNumber);
    }
}
