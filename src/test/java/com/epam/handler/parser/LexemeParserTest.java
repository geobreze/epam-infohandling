package com.epam.handler.parser;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LexemeParserTest {
    private static final String EXPRESSION = "[3_1_+_5_*]";
    private static final String WORD = "apple";
    private final Parser lexemeParser = new LexemeParser();

    @Test
    public void parseShouldReturnExpressionLexemeWhenExpressionStringSupplied() {
        Component expression = Lexeme.expression(EXPRESSION);
        Component parsedLexeme = lexemeParser.parse(EXPRESSION);

        Assert.assertEquals(parsedLexeme, expression);
    }

    @Test
    public void parseShouldReturnWordLexemeWhenWordStringSupplied() {
        Component word = Lexeme.word(WORD);
        Component parsedLexeme = lexemeParser.parse(WORD);

        Assert.assertEquals(parsedLexeme, word);
    }
}