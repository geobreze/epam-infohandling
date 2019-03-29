package com.epam.handler.restorer;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Lexeme;
import com.epam.handler.composite.LexemeType;

public class LexemeRestorer extends AbstractRestorer {
    private static final String EXPRESSION_OPEN_BRACKET = "[";
    private static final String EXPRESSION_CLOSE_BRACKET = "]";

    @Override
    public String restore(Component element) {
        Lexeme lexeme = (Lexeme) element;
        String restoredLexeme;
        if (lexeme.getType() == LexemeType.WORD) {
            restoredLexeme = lexeme.getValue();
        } else {
            restoredLexeme = EXPRESSION_OPEN_BRACKET + lexeme.getValue() + EXPRESSION_CLOSE_BRACKET;
        }
        return restoredLexeme;
    }
}
