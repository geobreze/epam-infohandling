package com.epam.handler.restorer;

import com.epam.handler.composite.Component;

import java.util.List;
import java.util.StringJoiner;

public class TextRestorer extends AbstractRestorer {
    private static final String PARAGRAPH_DELIMITER = "\n";

    @Override
    public String restore(Component component) {
        StringJoiner stringJoiner = new StringJoiner(PARAGRAPH_DELIMITER);
        List<String> restoredParagraphs = restoreEachChild(component);
        restoredParagraphs.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
}
