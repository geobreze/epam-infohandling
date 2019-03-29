package com.epam.handler.sorter;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;

import java.util.Comparator;
import java.util.List;

public class ParagraphsBySentenceQuantitySorter implements Sorter {
    private final Comparator<Component> SENTENCE_QUANTITY_COMPARATOR = Comparator
            .comparing(component -> component.getChildren().size());

    @Override
    public Component sort(Component text) {
        List<Component> paragraphs = text.getChildren();
        paragraphs.sort(SENTENCE_QUANTITY_COMPARATOR);
        Component sortedText = new Composite();
        paragraphs.forEach(sortedText::add);
        return sortedText;
    }
}
