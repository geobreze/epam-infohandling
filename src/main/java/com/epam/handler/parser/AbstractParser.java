package com.epam.handler.parser;

import com.epam.handler.composite.Component;
import com.epam.handler.composite.Composite;

public abstract class AbstractParser implements Parser {
    private Parser successor;

    protected Component parseEach(String[] parts) {
        Component composite = new Composite();
        for (String part : parts) {
            Component component = successor.parse(part);
            composite.add(component);
        }
        return composite;
    }

    public void setSuccessor(Parser successor) {
        this.successor = successor;
    }
}
