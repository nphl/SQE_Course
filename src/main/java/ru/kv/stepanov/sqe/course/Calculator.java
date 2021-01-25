package ru.kv.stepanov.sqe.course;

/*
 * Logic of this class is based on following BNF notation
 *
 * <expr> ::= <term> {<operation1> <term>}
 * <term> ::= <factor> {<operation2> <factor>}
 * <factor> ::= <unaryOp> <factor> | <number> | '('<expr>')'
 * <number> ::= <digit> {<digit>} [<separator> <digit> {<digit>}]
 * <unaryOp> ::= '+' | '-'
 *
 */

public class Calculator {

    /**
     * index of the position in {@code statement} string
     */
    private int positionIndex;

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result
     * @throws IllegalArgumentException if provided incorrect input string
     * @throws ArithmeticException      if calculated result is NaN of Infinite
     */
    public String evaluate(String statement) throws IllegalArgumentException, ArithmeticException {
        if (statement == null
                || statement.isEmpty()
                || statement.contains("++")
                || statement.contains("--")
                || statement.matches(".*[^\u0028-\u002B\u002D-\u0039].*")) {
            throw new IllegalArgumentException("Invalid statement string");
        }

        positionIndex = 0;
        double result = getExpr(statement);
        if (positionIndex < statement.length()) {
            throw new IllegalArgumentException("Invalid character '" + statement.charAt(positionIndex) + "' at position " + positionIndex);
        }

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            throw new ArithmeticException("Invalid result of evaluation");
        } else if (result == Math.floor(result)) {
            return String.format("%.0f", result);
        } else if (Double.toString(result).substring(Double.toString(result).indexOf('.')).length() > 5) {
            return String.format("%.4f", result);
        } else {
            return Double.toString(result);
        }

    }

    /**
     * Checking the character for accordance with definition of {@code <separator>}
     *
     * @param ch to check
     * @return result of compliance
     */
    private boolean isSeparator(char ch) {
        return ch == '.';
    }

    /**
     * Extracting from the string a substring corresponding to the definition of {@code <number>},
     * and calculating this number
     *
     * @param statement from which the substring is extracted
     * @return calculated number
     * @throws IllegalArgumentException if {@code statement} is invalid
     */
    private double getNumber(String statement) throws IllegalArgumentException {
        int initialPosition = positionIndex;

        if (positionIndex > statement.length() || !Character.isDigit(statement.charAt(positionIndex))) {
            throw new IllegalArgumentException("A digit is expected at position " + positionIndex);
        }

        do {
            positionIndex++;
        } while (positionIndex < statement.length() && Character.isDigit(statement.charAt(positionIndex)));

        if (positionIndex < statement.length() && isSeparator(statement.charAt(positionIndex))) {
            positionIndex++;

            if (positionIndex >= statement.length() || !Character.isDigit(statement.charAt(positionIndex))) {
                throw new IllegalArgumentException("A digit is expected at position " + positionIndex);
            }

            do {
                positionIndex++;
            } while (positionIndex < statement.length() && Character.isDigit(statement.charAt(positionIndex)));
        }

        return Double.parseDouble(statement.substring(initialPosition, positionIndex));
    }

    /**
     * Checking the character for compliance with {@code <operator1>}
     *
     * @param ch character to check
     * @return result of compliance
     */
    private boolean isOperator1(char ch) {
        return (ch == '+' || ch == '-');
    }

    /**
     * Checking the character for compliance with {@code <operator2>}
     *
     * @param ch character to check
     * @return result of compliance
     */
    private boolean isOperator2(char ch) {
        return (ch == '*' || ch == '/');
    }

    /**
     * Extracting from the string a substring corresponding to the definition of {@code <factor>},
     * and calculating it
     *
     * @param statement from which the substring is extracted
     * @return calculated {@code <factor>}
     * @throws IllegalArgumentException if {@code statement} is invalid or IllegalArgumentException occurred in used methods
     */
    private double getFactor(String statement) throws IllegalArgumentException {
        if (positionIndex >= statement.length()) {
            throw new IllegalArgumentException("Unexpected end of the line");
        }

        double result;
        switch (statement.charAt(positionIndex)) {
            case '+': // unary '+'
                positionIndex++;
                result = getFactor(statement);
                break;
            case '-': // unary '-'
                positionIndex++;
                result = -1 * getFactor(statement);
                break;
            case '(': // expression in parentheses
                positionIndex++;
                result = getExpr(statement);
                if (positionIndex >= statement.length() || statement.charAt(positionIndex) != ')') {
                    throw new IllegalArgumentException("Character ')' expected at position " + positionIndex);
                }
                positionIndex++;
                break;
            // numeric constant
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                result = getNumber(statement);
                break;
            default:
                throw new IllegalArgumentException("Invalid character '" + statement.charAt(positionIndex) + "' at position " + positionIndex);
        }

        return result;
    }

    /**
     * Extracting from the string a substring corresponding to the definition of {@code <term>},
     * and calculating it
     *
     * @param statement from which the substring is extracted
     * @return calculated {@code <term>}
     * @throws IllegalArgumentException if it's thrown by {@link Calculator#getFactor(String)}
     */
    private double getTerm(String statement) throws IllegalArgumentException {
        char operatorSymbol;
        double result = getFactor(statement);

        while (positionIndex < statement.length() && isOperator2(statement.charAt(positionIndex))) {
            operatorSymbol = statement.charAt(positionIndex);
            positionIndex++;

            switch (operatorSymbol) {
                case '*':
                    result = result * getFactor(statement);
                    break;
                case '/':
                    result = result / getFactor(statement);
                    break;
            }
        }

        return result;
    }

    /**
     * Extracting from the string a substring corresponding to the definition of {@code <expr>},
     * and calculating it
     *
     * @param statement from which the substring is extracted
     * @return calculated {@code <expr>}
     * @throws IllegalArgumentException if it's thrown by {@link Calculator#getTerm(String)}
     */
    private double getExpr(String statement) throws IllegalArgumentException {
        char operatorSymbol;

        double result = getTerm(statement);

        while (positionIndex < statement.length() && isOperator1(statement.charAt(positionIndex))) {
            operatorSymbol = statement.charAt(positionIndex);
            positionIndex++;

            switch (operatorSymbol) {
                case '+':
                    result = result + getTerm(statement);
                    break;
                case '-':
                    result = result - getTerm(statement);
                    break;
            }
        }

        return result;
    }

}
