package test;

import main.calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.*;

public class CalculatorTests {

    private Calculator calculator;

    @BeforeEach
    private void setup() {
        calculator = new Calculator();
    }

    @Test
    void divide_whenCalled_returnsCorrectResult() {
        var result = calculator.divide(100, 10);
        assertEquals(10, result, "100 divided by 10 should be 10");
    }

    @Test
    void divide_divisorIsZero_throwsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.divide(0,0));
        assertEquals("Can't divide by 0!", exception.getMessage());
    }

    @Test
    void add_whenCalled_returnsCorrectResult() {
        var result = calculator.add(1, 1);
        assertEquals(2, result);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4 })
    void square_whenCalled_returnsCorrectResult(double a) {
        assertEquals(Math.pow(a, 2), calculator.square(a));
    }

    @ParameterizedTest
    @MethodSource("multiplyTestDataProvider")
    void multiply_whenCalled_returnsCorrectResult(double a, double b, double expectedResult) {
        assertEquals(expectedResult, calculator.multiply(a, b));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> multiplyTestDataProvider() {
        return Stream.of(
                arguments(1,1,1),
                arguments(2,2,4),
                arguments(3,3,9),
                arguments(4,4,16),
                arguments(5,5,25),
                arguments(6,6,36),
                arguments(7,7,49),
                arguments(8,8,64),
                arguments(9,9,81),
                arguments(10,10,100),
                arguments(1,0,0)
        );
    }
}
