package com.epam.handler.parser;

import com.epam.handler.composite.Component;

public class TextParser extends AbstractParser {
    private static final String PARAGRAPH_DELIMITER = "\n";

    @Override
    public Component parse(String text) {
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);

        return parseEach(paragraphs);
    }
}