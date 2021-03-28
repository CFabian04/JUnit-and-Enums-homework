package org.siit;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.replaceAll;
import static org.siit.BinaryOperator.*;

public class StringExpression implements Expression {

    private List<Object> elements;


    public StringExpression(String expression) {
        String[] tokens = expression.trim().split("\\s+");
        elements = new ArrayList<>();

        if (tokens.length % 2 != 0) {
            for (int i = 0; i < tokens.length; ++i) {
                elements.add(i % 2 == 0
                        ? readAsNumber(tokens[i])
                        : readAsBinaryOperator(tokens[i]));
            }
        } else throw new ValidationException("The expression is incorrect");
    }

    public static Integer readAsNumber(String s) {
        if (s.length() == 0) {
            throw new ValidationException(
                    "No value was entered");
        }
        if (!isNumber(s)) {
            throw new ValidationException(
                    "Value '" + s + "' is not a number");
        }

        return Integer.parseInt(s);
    }

    public static BinaryOperator readAsBinaryOperator(String s) {

        for (BinaryOperator b : BinaryOperator.values()) {
            if (b.getSymbol().equals(s)) {
                return b;
            }
        }

        throw new ValidationException("The binary operator doesn't exist");

    }


    public static boolean isNumber(String str) {
        try {
            //the next commented line is intentionally incorrect
            //so exception causes and re-throw can be tested
            //for (int i=0; i<=str.length(); ++i) {
            for (int i = 0; i < str.length(); ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(
                    "Error checking if '" + str + "' is a digit", e);
        }

    }

    public void convertElements() {

        replaceAll(elements, ADD, ADD.getSymbol());
        replaceAll(elements, SUBTRACT, SUBTRACT.getSymbol());
        replaceAll(elements, MULTIPLY, MULTIPLY.getSymbol());
        replaceAll(elements, DIVIDE, DIVIDE.getSymbol());
        replaceAll(elements, MODULUS, MODULUS.getSymbol());
    }

    public List<Object> getElements() {
        return elements;
    }
}
