package ru.kv.stepanov.sqe.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorCourseWorkTests {

    private static Calculator calc;

    @BeforeAll
    static void init() {
        calc = new Calculator();
    }

    @Test
    public void evaluateTwoToThePowerOfTwoPoweredOfTwo() {
        // given
        String input = "2^2^2";
        String expectedResult = "16";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateTwoToThePowerOfThreePoweredOfTwo() {
        // given
        String input = "2^3^2";
        String expectedResult = "512";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateTwoToThePowerOfTwoPoweredOfThree() {
        // given
        String input = "2^2^3";
        String expectedResult = "256";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateTwoToThePowerOfTwoPlusTwo() {
        // given
        String input = "2^2+2";
        String expectedResult = "6";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateTwoPlusTwoToThePowerOfTwoPlusTwo() {
        // given
        String input = "2+2^2+2";
        String expectedResult = "8";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateTwoPlusTwoToThePowerOfTwoPlusTwoInBrackets() {
        // given
        String input = "2+2^(2+2)";
        String expectedResult = "18";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateTwoPlusTwoInBracketsToThePowerOfTwoPlusTwo() {
        // given
        String input = "(2+2)^2+2";
        String expectedResult = "18";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateTwoPlusTwoInBracketsToThePowerOfTwoPlusTwoInBrackets() {
        // given
        String input = "(2+2)^(2+2)";
        String expectedResult = "256";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluatePowerOfAfterPowerOf() {
        // given
        String input = "2^^3";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluatePowerOfAfterPowerOfAfterPowerOf() {
        // given
        String input = "2^^^3";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

}
