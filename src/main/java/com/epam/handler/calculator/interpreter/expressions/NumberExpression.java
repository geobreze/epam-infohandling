package com.epam.handler.calculator.interpreter.expressions;

import java.util.Deque;

public class NumberExpression implements Expression {
    private final Integer number;

    public NumberExpression(Integer number) {
        this.number = number;
    }

    @Override
    public void interpret(Deque<Integer> context) {
        context.push(number);
    }
}
