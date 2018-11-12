package de.esolutions.lang.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    private static final String TESTFILE_NAME = "futest.txt";
    private static final Integer TESTFILE_WORDCOUNT = new Integer(12);

    @Test
    void printEachLineTest() {
        try {
            FileUtils.forEachLine(TESTFILE_NAME, System.out::println); // , Charset.forName("Windows-1252"));
            assertTrue(true);
        } catch (IOException exc) {
            exc.printStackTrace();
            fail(exc);
        }
    }

    @Test
    void printLineWordCount() {
        try {
            FileUtils.forEachLine(TESTFILE_NAME,(line) -> {
                System.out.println("[" + line.length() + ":" + line.split("\\w+").length + "] " + line);
            });
            assertTrue(true);
        } catch (IOException exc) {
            exc.printStackTrace();
            fail(exc);
        }
    }

    @Test
    void countNumberOfWordsInFile() {
        try {
            Integer wordCount =
                FileUtils.reduce(
                    TESTFILE_NAME,
                    new Integer(0),
                    (count, line) ->  count + line.split("\\s+").length
                );
            assertEquals(TESTFILE_WORDCOUNT, wordCount);
        } catch (IOException exc) {
            exc.printStackTrace();
            fail(exc);
        }
    }

}