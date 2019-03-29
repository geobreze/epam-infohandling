package com.epam.handler.sorter;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSorter implements Sorter {
    protected Component sortLexemesInSentence(Component sentence, Comparator<Lexeme> comparator) {
        List<Component> children = sentence.getChildren();
        List<Lexeme> sortedLexemes = children
                .stream()
                .map(component -> (Lexeme) component)
                .sorted(comparator)
                .collect(Collectors.toList());
        Component sortedSentence = new Composite();
        sortedLexemes.forEach(sortedSentence::add);
        return sortedSentence;
    }
}
