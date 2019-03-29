package com.epam.handler.parser;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TextParserTest {
    private static final String TEXT = "There are many.\n" +
            "But the majority?";

    @Test
    public void parseShouldReturnTextCompositeWhenCorrectTextSupplied() {
        AbstractParser paragraphParser = mock(AbstractParser.class);
        Component firstParagraph = new Composite();
        Component firstSentence = new Composite();
        firstSentence.add(Lexeme.word("There"));
        firstSentence.add(Lexeme.word("are"));
        firstSentence.add(Lexeme.word("many"));
        firstParagraph.add(firstSentence);
        when(paragraphParser.parse("There are many.")).thenReturn(firstParagraph);
        Component secondParagraph = new Composite();
        Component secondSentence = new Composite();
        secondSentence.add(Lexeme.word("But"));
        secondSentence.add(Lexeme.word("the"));
        secondSentence.add(Lexeme.word("majority"));
        secondParagraph.add(secondSentence);
        when(paragraphParser.parse("But the majority?")).thenReturn(secondParagraph);

        AbstractParser textParser = new TextParser();
        textParser.setSuccessor(paragraphParser);
        Component parsedText = textParser.parse(TEXT);
        List<Component> paragraphList = parsedText.getChildren();

        List<Component> expectedList = Arrays.asList(
                firstParagraph,
                secondParagraph
        );

        Assert.assertEquals(paragraphList, expectedList);
        verify(paragraphParser).parse("There are many.");
        verify(paragraphParser).parse("But the majority?");
        verifyNoMoreInteractions(paragraphParser);

    }
}
