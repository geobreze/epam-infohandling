package com.epam.handler.restorer;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class TextRestorerTest {
    private static final String TEXT_STRING = "There are many.\n" +
            "But the majority.";

    @Test
    public void restoreShouldReturnParagraphsSpiltByNewLineSymbolWhenParagraphComponentSupplied() {
        AbstractRestorer paragraphRestorer = mock(AbstractRestorer.class);
        Component firstParagraph = new Composite();
        Component firstSentence = new Composite();
        firstSentence.add(Lexeme.word("There"));
        firstSentence.add(Lexeme.word("are"));
        firstSentence.add(Lexeme.word("many"));
        firstParagraph.add(firstSentence);
        when(paragraphRestorer.restore(firstParagraph)).thenReturn("There are many.");
        Component secondParagraph = new Composite();
        Component secondSentence = new Composite();
        secondSentence.add(Lexeme.word("But"));
        secondSentence.add(Lexeme.word("the"));
        secondSentence.add(Lexeme.word("majority"));
        secondParagraph.add(secondSentence);
        when(paragraphRestorer.restore(secondParagraph)).thenReturn("But the majority.");

        Component text = new Composite();
        text.add(firstParagraph);
        text.add(secondParagraph);

        AbstractRestorer textRestorer = new TextRestorer();
        textRestorer.setSuccessor(paragraphRestorer);
        String restoredText = textRestorer.restore(text);

        Assert.assertEquals(restoredText, TEXT_STRING);
        verify(paragraphRestorer).restore(firstParagraph);
        verify(paragraphRestorer).restore(secondParagraph);
        verifyNoMoreInteractions(paragraphRestorer);
    }
}