package com.epam.handler.restorer.builder;

import com.epam.handler.restorer.*;

public class TextRestorerChainBuilder implements RestorerBuilder {

    @Override
    public Restorer build() {
        AbstractRestorer lexemeRestorer = new LexemeRestorer();
        AbstractRestorer sentenceRestorer = new SentenceRestorer();
        sentenceRestorer.setSuccessor(lexemeRestorer);

        AbstractRestorer paragraphRestorer = new ParagraphRestorer();
        paragraphRestorer.setSuccessor(sentenceRestorer);

        AbstractRestorer textRestorer = new TextRestorer();
        textRestorer.setSuccessor(paragraphRestorer);

        return textRestorer;
    }
}
