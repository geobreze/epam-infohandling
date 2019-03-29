package com.epam.handler.calculator.interpreter;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostfixExpressionInterpreterTest {
    private final Interpreter interpreter = new PostfixExpressionInterpreter();

    @DataProvider
    public static Object[][] correctExpressionsProvider() {
        return new Object[][]{
                new Object[]{"15_3_+", 18},
                new Object[]{"15_3_-", 12},
                new Object[]{"15_3_*", 45},
                new Object[]{"15_3_/", 5},
                new Object[]{"3_4_5_10_-_*_5_/_+_4_-", -5}
        };
    }

    @DataProvider
    public static Object[][] incorrectExpressionsProvider() {
        return new Object[][]{
                new Object[]{"15_3_*+"},
                new Object[]{"_3_/"},
                new Object[]{"o3_4_5_10_-_*_5_/_+_4_-"}
        };
    }

    @Test(dataProvider = "correctExpressionsProvider")
    public void interpretShouldCalculateCorrectlyWhenCorrectExpressionSupplied(String expression, Integer answer) {
        Integer calculated = interpreter.interpret(expression);

        Assert.assertEquals(calculated, answer);
    }

    @Test(dataProvider = "incorrectExpressionsProvider", expectedExceptions = IllegalArgumentException.class)
    public void interpretShouldThrowIllegalArgumentExceptionWhenIncorrectExpressionSupplied(String expression) {
        interpreter.interpret(expression);
    }
}