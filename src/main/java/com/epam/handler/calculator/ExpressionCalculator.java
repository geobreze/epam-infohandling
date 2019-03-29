package com.epam.handler.calculator;

import com.epam.handler.calculator.interpreter.Interpreter;
import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import com.epam.handler.composite.LexemeType;

import java.util.List;

public class ExpressionCalculator {
    private final Interpreter interpreter;

    public ExpressionCalculator(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public Component calculate(Component component) {
        Component calculatedComponent;
        if (component.getClass() == Lexeme.class) {
            Lexeme lexeme = (Lexeme) component;
            calculatedComponent = substituteWord(lexeme);
        } else {
            calculatedComponent = calculateForEachChild(component);
        }
        return calculatedComponent;
    }

    private Component calculateForEachChild(Component component) {
        Component calculatedComponent = new Composite();
        List<Component> children = component.getChildren();
        for (Component child : children) {
            Component recalculatedChild = calculate(child);
            calculatedComponent.add(recalculatedChild);
        }
        return calculatedComponent;
    }

    private Component substituteWord(Lexeme lexeme) {
        String value = lexeme.getValue();
        if (lexeme.getType() == LexemeType.EXPRESSION) {
            Integer calculatedExpression = interpreter.interpret(value);
            value = calculatedExpression.toString();
        }
        return Lexeme.word(value);
    }
}
