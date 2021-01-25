package ru.kv.stepanov.sqe.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    private static Calculator calc;

    @BeforeAll
    static void init() {
        calc = new Calculator();
    }

    @Test
    public void evaluateAddition() {
        // given
        String input = "2+3";
        String expectedResult = "5";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateSubtraction() {
        // given
        String input = "4-6";
        String expectedResult = "-2";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateMultiplication() {
        // given
        String input = "2*3";
        String expectedResult = "6";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateDivision() {
        // given
        String input = "12/3";
        String expectedResult = "4";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateAddMultStatement() {
        // given
        String input = "2+3*4";
        String expectedResult = "14";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateDivSubAddMultStatement() {
        // given
        String input = "10/2-7+3*4";
        String expectedResult = "10";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateDivBracketsMultStatement() {
        // given
        String input = "10/(2-7+3)*4";
        String expectedResult = "-20";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateDivMultDot1() {
        // given
        String input = "22/3*3.0480";
        String expectedResult = "22.352";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateDivMultDot2() {
        // given
        String input = "22/4*2.1591";
        String expectedResult = "11.8751";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateAddMaxDoubles() {
        String input = String.format("%.0f+%.0f", Double.MAX_VALUE, Double.MAX_VALUE);

        // assert
        Assertions.assertThrows(ArithmeticException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateSubMaxDoubles() {
        // given
        String input = String.format("%.0f-%.0f", Double.MAX_VALUE, Double.MAX_VALUE);
        String expectedResult = "0";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateDivMaxDoubles() {
        // given
        String input = String.format("%.0f/%.0f", Double.MAX_VALUE, Double.MAX_VALUE);
        String expectedResult = "1";

        // run
        String result = calc.evaluate(input);

        // assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluateMultMaxDoubles() {
        // given
        String input = String.format("%.0f*%.0f", Double.MAX_VALUE, Double.MAX_VALUE);

        // run and assert
        Assertions.assertThrows(ArithmeticException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateMaxDoubleByZeroDiv() {
        // given
        String input = String.format("%.0f/0", Double.MAX_VALUE);

        // run and assert
        Assertions.assertThrows(ArithmeticException.class, () -> calc.evaluate(input));
    }

}
