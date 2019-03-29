package com.epam.handler.restorer;

import com.epam.handler.composite.Component;

import java.util.List;
import java.util.StringJoiner;

public class SentenceRestorer extends AbstractRestorer {
    private static final String LEXEME_DELIMITER = " ";

    @Override
    public String restore(Component element) {
        StringJoiner stringJoiner = new StringJoiner(LEXEME_DELIMITER);
        List<String> restoredParagraphs = restoreEachChild(element);
        restoredParagraphs.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
}
