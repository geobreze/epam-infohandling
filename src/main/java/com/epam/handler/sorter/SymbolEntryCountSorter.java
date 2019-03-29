package com.epam.handler.sorter;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Lexeme;

import java.util.Comparator;

public class SymbolEntryCountSorter extends AbstractSorter {
    private final char symbol;

    public SymbolEntryCountSorter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public Component sort(Component sentence) {
        Comparator<Lexeme> symbolEntryCountComparator = Comparator
                .comparing(this::countNumberOfEntries)
                .thenComparing(lexeme -> lexeme.getValue().toLowerCase());
        return sortLexemesInSentence(sentence, symbolEntryCountComparator);
    }

    private long countNumberOfEntries(Lexeme lexeme) {
        String value = lexeme.getValue();
        return value
                .chars()
                .map(character -> (char) character)
                .filter(character -> character == symbol)
                .count();
    }
}
