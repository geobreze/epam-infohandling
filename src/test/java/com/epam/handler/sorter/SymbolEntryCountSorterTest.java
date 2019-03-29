package com.epam.handler.sorter;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SymbolEntryCountSorterTest {
    private static final char DESIRED_SYMBOL = 'a';

    @Test
    public void sortShouldSortLexemesInNaturalOrderWhenSentenceSupplied() {
        Component sentence = new Composite();
        sentence.add(Lexeme.word("bbaaaa"));
        sentence.add(Lexeme.word("cdb"));
        sentence.add(Lexeme.word("caaaaaaa"));
        sentence.add(Lexeme.word("aaaa"));

        Component expectedSentence = new Composite();
        expectedSentence.add(Lexeme.word("cdb"));
        expectedSentence.add(Lexeme.word("aaaa"));
        expectedSentence.add(Lexeme.word("bbaaaa"));
        expectedSentence.add(Lexeme.word("caaaaaaa"));

        Sorter sorter = new SymbolEntryCountSorter(DESIRED_SYMBOL);
        Component sortedSentence = sorter.sort(sentence);

        Assert.assertEquals(sortedSentence, expectedSentence);
    }
}