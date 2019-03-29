package com.epam.handler.restorer;

import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LexemeRestorerTest {
    private static final String EXPRESSION_STRING = "[10_20_+]";
    private static final String WORD_STRING = "word";
    private static final Lexeme EXPRESSION = Lexeme.expression("10_20_+");
    private static final Lexeme WORD = Lexeme.word("word");
    private final LexemeRestorer restorer = new LexemeRestorer();

    @Test
    public void restoreShouldReturnExpressionInBracketsWhenCorrectExpressionSupplied() {
        String restoredExpression = restorer.restore(EXPRESSION);

        Assert.assertEquals(restoredExpression, EXPRESSION_STRING);
    }

    @Test
    public void restoreShouldReturnWordValueWhenWordSupplied() {
        String restoredWord = restorer.restore(WORD);

        Assert.assertEquals(restoredWord, WORD_STRING);
    }
}