package com.epam.handler.calculator;

import com.epam.handler.calculator.interpreter.Interpreter;
import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class ExpressionCalculatorTest {
    private static final String ONE_PLUS_TWO = "1_2_+";
    private static final String ONE_MUL_TWO = "1_2_*";

    @Test
    public void calculateShouldReturnComponentWithCalculatedExpressionsWhenCorrectTextSupplied() {
        Component firstSentence = new Composite();
        firstSentence.add(Lexeme.word("First"));
        firstSentence.add(Lexeme.expression(ONE_PLUS_TWO));
        firstSentence.add(Lexeme.word("second"));
        Component secondSentence = new Composite();
        secondSentence.add(Lexeme.word("Third"));
        secondSentence.add(Lexeme.expression(ONE_MUL_TWO));
        Component firstParagraph = new Composite();
        firstParagraph.add(firstSentence);
        Component secondParagraph = new Composite();
        secondParagraph.add(secondSentence);
        Component text = new Composite();
        text.add(firstParagraph);
        text.add(secondParagraph);

        Component firstExpectedSentence = new Composite();
        firstExpectedSentence.add(Lexeme.word("First"));
        firstExpectedSentence.add(Lexeme.word("3"));
        firstExpectedSentence.add(Lexeme.word("second"));
        Component secondExpectedSentence = new Composite();
        secondExpectedSentence.add(Lexeme.word("Third"));
        secondExpectedSentence.add(Lexeme.word("2"));
        Component firstExpectedParagraph = new Composite();
        firstExpectedParagraph.add(firstExpectedSentence);
        Component secondExpectedParagraph = new Composite();
        secondExpectedParagraph.add(secondExpectedSentence);
        Component expectedText = new Composite();
        expectedText.add(firstExpectedParagraph);
        expectedText.add(secondExpectedParagraph);

        Interpreter interpreter = mock(Interpreter.class);
        when(interpreter.interpret(ONE_PLUS_TWO)).thenReturn(3);
        when(interpreter.interpret(ONE_MUL_TWO)).thenReturn(2);
        ExpressionCalculator calculator = new ExpressionCalculator(interpreter);
        Component calculatedText = calculator.calculate(text);

        Assert.assertEquals(calculatedText, expectedText);
        verify(interpreter).interpret(ONE_PLUS_TWO);
        verify(interpreter).interpret(ONE_MUL_TWO);
        verifyNoMoreInteractions(interpreter);
    }

}