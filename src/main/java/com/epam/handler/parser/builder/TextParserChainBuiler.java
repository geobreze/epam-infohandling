package com.epam.handler.parser.builder;

import com.epam.handler.parser.*;

public class TextParserChainBuiler implements ParserBuilder {
    @Override
    public Parser build() {
        AbstractParser lexemeParser = new LexemeParser();
        AbstractParser sentenceParser = new SentenceParser();
        sentenceParser.setSuccessor(lexemeParser);

        AbstractParser paragraphParser = new ParagraphParser();
        paragraphParser.setSuccessor(sentenceParser);

        AbstractParser textParser = new TextParser();
        textParser.setSuccessor(paragraphParser);

        return textParser;
    }
}
