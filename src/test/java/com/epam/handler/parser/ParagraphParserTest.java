package com.epam.handler.parser;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ParagraphParserTest {
    private static final String PARAGRAPH = "Nam, gam. " +
            "Sed li? " +
            "Quisque... " +
            "Phasellus!";

    @Test
    public void parseShouldReturnParagraphCompositeWhenCorrectParagraphSupplied() {
        AbstractParser sentenceParser = mock(AbstractParser.class);

        Component firstSentence = new Composite();
        firstSentence.add(Lexeme.word("Nam"));
        firstSentence.add(Lexeme.word("gam"));
        when(sentenceParser.parse("Nam, gam")).thenReturn(firstSentence);

        Component secondSentence = new Composite();
        secondSentence.add(Lexeme.word("Sed"));
        secondSentence.add(Lexeme.word("li"));
        when(sentenceParser.parse("Sed li")).thenReturn(secondSentence);

        Component thirdSentence = new Composite();
        thirdSentence.add(Lexeme.word("Quisque"));
        when(sentenceParser.parse("Quisque")).thenReturn(thirdSentence);

        Component fourthSentence = new Composite();
        fourthSentence.add(Lexeme.word("Phasellus"));
        when(sentenceParser.parse("Phasellus")).thenReturn(fourthSentence);

        AbstractParser paragraphParser = new ParagraphParser();
        paragraphParser.setSuccessor(sentenceParser);
        Component paragraph = paragraphParser.parse(PARAGRAPH);
        List<Component> sentenceList = paragraph.getChildren();

        List<Component> expectedList = Arrays.asList(
                firstSentence,
                secondSentence,
                thirdSentence,
                fourthSentence
        );

        Assert.assertEquals(sentenceList, expectedList);
        verify(sentenceParser, times(expectedList.size())).parse(anyString());
        verifyNoMoreInteractions(sentenceParser);
    }
}