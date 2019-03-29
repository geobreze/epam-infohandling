package com.epam.handler.restorer;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class ParagraphRestorerTest {
    private static final String PARAGRAPH_STRING = "Nam gam. " +
            "Sed li.";

    @Test
    public void restoreShouldReturnSentencesSplitByDotWhenDifferentSentencesSupplied() {
        AbstractRestorer sentenceRestorer = mock(AbstractRestorer.class);
        Component firstSentence = new Composite();
        firstSentence.add(Lexeme.word("Nam"));
        firstSentence.add(Lexeme.word("gam"));
        when(sentenceRestorer.restore(firstSentence)).thenReturn("Nam gam");
        Component secondSentence = new Composite();
        secondSentence.add(Lexeme.word("Sed"));
        secondSentence.add(Lexeme.word("li"));
        when(sentenceRestorer.restore(secondSentence)).thenReturn("Sed li");

        Component paragraph = new Composite();
        paragraph.add(firstSentence);
        paragraph.add(secondSentence);

        AbstractRestorer paragraphRestorer = new ParagraphRestorer();
        paragraphRestorer.setSuccessor(sentenceRestorer);
        String restoredParagraph = paragraphRestorer.restore(paragraph);

        Assert.assertEquals(restoredParagraph, PARAGRAPH_STRING);
        verify(sentenceRestorer).restore(firstSentence);
        verify(sentenceRestorer).restore(secondSentence);
        verifyNoMoreInteractions(sentenceRestorer);
    }
}