package com.epam.handler.reader;

import com.epam.handler.exception.InvalidFileException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileReaderTest {
    private static final String CORRECT_FILE_PATH = "src/test/resources/correct_file.txt";
    private static final String INCORRECT_PATH = "incorrect";
    private static final String CORRECT_FILE_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
            "Donec a sem faucibus risus fringilla consequat et at ante.\n" +
            "Sed ipsum lacus, faucibus at diam nec, finibus tempor justo. Sed vulputate.";
    private final Reader fileReader = new FileReader();

    @Test
    public void readShouldReturnSeparatedLinesWhenCorrectFileSupplied() throws InvalidFileException {
        String readResult = fileReader.read(CORRECT_FILE_PATH);
        Assert.assertEquals(readResult, CORRECT_FILE_CONTENT);
    }

    @Test(expectedExceptions = InvalidFileException.class)
    public void readShouldThrowInvalidFileExceptionWhenIncorrectFileSupplied() throws InvalidFileException {
        fileReader.read(INCORRECT_PATH);
    }
}