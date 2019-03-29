package com.epam.handler.sorter;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParagraphsBySentenceQuantitySorterTest {

    @Test
    public void sortShouldSortInNaturalOrderWhenCorrectTextSupplied() {
        Component firstSentence = new Composite();
        firstSentence.add(Lexeme.word("One"));
        firstSentence.add(Lexeme.word("two"));
        firstSentence.add(Lexeme.word("three"));
        Component secondSentence = new Composite();
        firstSentence.add(Lexeme.word("Four"));
        firstSentence.add(Lexeme.word("five"));
        firstSentence.add(Lexeme.word("six"));
        Component thirdSentence = new Composite();
        firstSentence.add(Lexeme.word("Seven"));
        firstSentence.add(Lexeme.word("eight"));
        firstSentence.add(Lexeme.word("nine"));

        Component firstParagraph = new Composite();
        firstParagraph.add(firstSentence);
        firstParagraph.add(secondSentence);
        Component secondParagraph = new Composite();
        secondParagraph.add(thirdSentence);

        Component text = new Composite();
        text.add(firstParagraph);
        text.add(secondParagraph);

        Sorter paragraphsSorter = new ParagraphsBySentenceQuantitySorter();
        Component sortedText = paragraphsSorter.sort(text);

        Component expectedText = new Composite();
        expectedText.add(secondParagraph);
        expectedText.add(firstParagraph);

        Assert.assertEquals(sortedText, expectedText);
    }
}