package com.epam.handler.sorter;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EachSentenceWordsByLengthSorterTest {

    @Test
    public void sortShouldSortWordsByLengthWhenTextCompositeSupplied() {
        Component firstSentence = new Composite();
        firstSentence.add(Lexeme.word("Hydrogen"));
        firstSentence.add(Lexeme.word("two"));
        firstSentence.add(Lexeme.word("plain"));
        Component secondSentence = new Composite();
        secondSentence.add(Lexeme.word("Sorted"));
        secondSentence.add(Lexeme.word("sentence"));
        secondSentence.add(Lexeme.word("not"));
        Component firstParagraph = new Composite();
        firstParagraph.add(firstSentence);
        Component secondParagraph = new Composite();
        secondParagraph.add(secondSentence);
        Component text = new Composite();
        text.add(firstParagraph);
        text.add(secondParagraph);

        Component firstExpectedSentence = new Composite();
        firstExpectedSentence.add(Lexeme.word("two"));
        firstExpectedSentence.add(Lexeme.word("plain"));
        firstExpectedSentence.add(Lexeme.word("Hydrogen"));
        Component secondExpectedSentence = new Composite();
        secondExpectedSentence.add(Lexeme.word("not"));
        secondExpectedSentence.add(Lexeme.word("Sorted"));
        secondExpectedSentence.add(Lexeme.word("sentence"));
        Component firstExpectedParagraph = new Composite();
        firstExpectedParagraph.add(firstExpectedSentence);
        Component secondExpectedParagraph = new Composite();
        secondExpectedParagraph.add(secondExpectedSentence);
        Component expectedText = new Composite();
        expectedText.add(firstExpectedParagraph);
        expectedText.add(secondExpectedParagraph);

        Sorter sorter = new EachSentenceWordsByLengthSorter();
        Component sortedText = sorter.sort(text);

        Assert.assertEquals(sortedText, expectedText);
    }
}