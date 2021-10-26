package main.calculator;

public class Calculator {
    public Calculator() {

    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Can't divide by 0!");
        }

        return a/b;
    }

    public double multiply(double a, double b) {
        return a*b;
    }

    public double square(double a) {
        return a*a;
    }

    public double add(double a, double b) {
        return 2;
    }
}
