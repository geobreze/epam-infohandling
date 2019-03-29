package com.epam.handler.parser;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Lexeme;

public class LexemeParser extends AbstractParser {
    private static final String EXPRESSION_PATTERN = "^\\[\\d+_\\d+_((\\d+|([+\\-*/]))_)*([+\\-*/])]$";

    @Override
    public Component parse(String text) {
        Component lexeme;
        boolean isExpression = text.matches(EXPRESSION_PATTERN);
        if (isExpression) {
            String expression = text.substring(1, text.length() - 1);
            lexeme = Lexeme.expression(expression);
        } else {
            lexeme = Lexeme.word(text);
        }
        return lexeme;
    }
}
