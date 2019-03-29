package com.epam.handler.parser;

import com.epam.handler.composite.Component;

public interface Parser {
    Component parse(String text);
}
