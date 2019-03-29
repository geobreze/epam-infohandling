package com.epam.handler.calculator.interpreter.expressions;

import java.util.Deque;

public interface Expression {
    void interpret(Deque<Integer> context);
}
