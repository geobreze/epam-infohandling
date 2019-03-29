package com.epam.handler.reader;

import com.epam.handler.exception.InvalidFileException;

public interface Reader {
    String read(String source) throws InvalidFileException;
}
