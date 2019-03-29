package com.epam.handler.calculator.interpreter;

import com.epam.handler.calculator.interpreter.expressions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostfixExpressionInterpreter implements Interpreter {
    private static final String EXPRESSION_DELIMITER = "_";

    public Integer interpret(String expression) {
        Deque<Integer> context = new ArrayDeque<>();
        List<Expression> parsedExpressions = parse(expression);

        for (Expression mathExpression : parsedExpressions) {
            mathExpression.interpret(context);
        }

        return context.pop();
    }

    private List<Expression> parse(String expression) {
        String[] mathExpressionsList = expression.split(EXPRESSION_DELIMITER);
        List<Expression> parsedExpressions = new ArrayList<>();
        for (String mathExpression : mathExpressionsList) {
            Expression createdExpression = createExpression(mathExpression);
            parsedExpressions.add(createdExpression);
        }
        return parsedExpressions;
    }

    private Expression createExpression(String value) {
        Expression expression;
        switch (value) {
            case "+": {
                expression = new PlusExpression();
                break;
            }
            case "-": {
                expression = new MinusExpression();
                break;

            }
            case "*": {
                expression = new MultiplyExpression();
                break;

            }
            case "/": {
                expression = new DivideExpression();
                break;
            }
            default: {
                Integer number = Integer.parseInt(value);
                expression = new NumberExpression(number);
            }
        }
        return expression;
    }
}
