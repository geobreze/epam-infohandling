package com.epam.handler.calculator.interpreter.expressions;

import java.util.Deque;

public class MinusExpression implements Expression {
    @Override
    public void interpret(Deque<Integer> context) {
        Integer secondElement = context.pop();
        Integer firstElement = context.pop();
        context.push(firstElement - secondElement);
    }
}
