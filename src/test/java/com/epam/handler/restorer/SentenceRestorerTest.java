package com.epam.handler.restorer;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class SentenceRestorerTest {
    private static final String SENTENCE_STRING = "Lorem [2_3_*] sit [1_2_-_3_4_*_/]";
    private static final int NUMBER_OF_WORDS = 4;

    @Test
    public void restoreShouldReturnRestoredSentenceWithoutPunctuationWhenCorrectSentenceSupplied() {
        AbstractRestorer lexemeRestorer = mock(AbstractRestorer.class);

        when(lexemeRestorer.restore(Lexeme.word("Lorem"))).thenReturn("Lorem");
        when(lexemeRestorer.restore(Lexeme.expression("2_3_*"))).thenReturn("[2_3_*]");
        when(lexemeRestorer.restore(Lexeme.word("sit"))).thenReturn("sit");
        when(lexemeRestorer.restore(Lexeme.expression("1_2_-_3_4_*_/"))).thenReturn("[1_2_-_3_4_*_/]");

        Component sentence = new Composite();
        sentence.add(Lexeme.word("Lorem"));
        sentence.add(Lexeme.expression("2_3_*"));
        sentence.add(Lexeme.word("sit"));
        sentence.add(Lexeme.expression("1_2_-_3_4_*_/"));

        AbstractRestorer restorer = new SentenceRestorer();
        restorer.setSuccessor(lexemeRestorer);
        String restoredSentence = restorer.restore(sentence);

        Assert.assertEquals(restoredSentence, SENTENCE_STRING);
        verify(lexemeRestorer, times(NUMBER_OF_WORDS)).restore(any(Component.class));
        verifyNoMoreInteractions(lexemeRestorer);
    }
}