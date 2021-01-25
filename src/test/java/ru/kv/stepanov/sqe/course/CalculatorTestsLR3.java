package ru.kv.stepanov.sqe.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorTestsLR3 {

    private static Calculator calc;

    @BeforeAll
    static void init() {
        calc = new Calculator();
    }

    @Test
    public void evaluateComma() {
        // given
        String input = "22/4*2,159";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateRandomSequence() {
        // given
        String input = "- 12)1//(";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateDivByZero() {
        // given
        String input = "10/(5-5)";

        // assert
        Assertions.assertThrows(ArithmeticException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateNull() {
        // given
        String input = null;

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateWORightBracket() {
        // given
        String input = "(12*(5-1)";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateEmptyLine() {
        // given
        String input = "";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateDuplicatedDot() {
        // given
        String input = "5+41..1-6";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateDuplicatedPlus() {
        // given
        String input = "5++41-6";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateDuplicatedMinus() {
        // given
        String input = "5--41-6";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateDuplicatedStar() {
        // given
        String input = "5**41-6";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateDuplicatedSlash() {
        // given
        String input = "5//41-6";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }

    @Test
    public void evaluateSignAfterNumber() {
        // given
        String input = "5.43-";

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.evaluate(input));
    }
}
