package com.epam.handler.parser;

import com.epam.handler.composite.Component;

public class SentenceParser extends AbstractParser {
    private static String LEXEME_DELIMITER = ",? ";

    @Override
    public Component parse(String text) {
        String[] lexemes = text.split(LEXEME_DELIMITER);

        return parseEach(lexemes);
    }
}
