package com.epam.handler.reader;

import com.epam.handler.exception.InvalidFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class FileReader implements Reader {
    private static final String LINE_DELIMITER = "\n";

    @Override
    public String read(String source) throws InvalidFileException {
        Path path = Paths.get(source);
        StringJoiner stringJoiner = new StringJoiner(LINE_DELIMITER);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(stringJoiner::add);
        } catch (IOException e) {
            throw new InvalidFileException(e);
        }
        return stringJoiner.toString();
    }
}
