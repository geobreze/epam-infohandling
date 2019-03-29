package com.epam.handler.sorter;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;
import com.epam.handler.composite.Lexeme;

import java.util.Comparator;
import java.util.List;

public class EachSentenceWordsByLengthSorter extends AbstractSorter {
    private static final Comparator<Lexeme> LEXEME_LENGTH_COMPARATOR = Comparator
            .comparing(lexeme -> lexeme.getValue().length());

    @Override
    public Component sort(Component text) {
        Component sortedText = new Composite();
        List<Component> paragraphs = text.getChildren();
        for (Component paragraph : paragraphs) {
            Component sortedParagraph = sortSentences(paragraph);
            sortedText.add(sortedParagraph);
        }
        return sortedText;
    }

    private Component sortSentences(Component paragraph) {
        List<Component> sentences = paragraph.getChildren();
        Component sortedParagraph = new Composite();
        for (Component sentence : sentences) {
            Component sortedSentence = sortLexemesInSentence(sentence, LEXEME_LENGTH_COMPARATOR);
            sortedParagraph.add(sortedSentence);
        }
        return sortedParagraph;
    }

}
