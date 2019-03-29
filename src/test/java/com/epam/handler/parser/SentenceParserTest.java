package com.epam.handler.parser;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SentenceParserTest {
    private static final String SENTENCE = "Lorem [2_3_*] sit, [1_2_-_3_4_*_/]";

    @Test
    public void parseShouldReturnSentenceComponentWhenCorrectSentenceSupplied() {
        AbstractParser lexemeParser = mock(AbstractParser.class);

        when(lexemeParser.parse("Lorem")).thenReturn(Lexeme.word("Lorem"));
        when(lexemeParser.parse("[2_3_*]")).thenReturn(Lexeme.expression("2_3_*"));
        when(lexemeParser.parse("sit")).thenReturn(Lexeme.word("sit"));
        when(lexemeParser.parse("[1_2_-_3_4_*_/]")).thenReturn(Lexeme.expression("1_2_-_3_4_*_/"));

        AbstractParser sentenceParser = new SentenceParser();
        sentenceParser.setSuccessor(lexemeParser);
        Component sentenceComponent = sentenceParser.parse(SENTENCE);
        List<Component> wordsList = sentenceComponent.getChildren();

        List<Component> expectedList = Arrays.asList(
                Lexeme.word("Lorem"),
                Lexeme.expression("2_3_*"),
                Lexeme.word("sit"),
                Lexeme.expression("1_2_-_3_4_*_/")
        );

        Assert.assertEquals(wordsList, expectedList);
        verify(lexemeParser, times(expectedList.size())).parse(anyString());
        verifyNoMoreInteractions(lexemeParser);
    }

}
