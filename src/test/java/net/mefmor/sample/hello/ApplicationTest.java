package net.mefmor.sample.hello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * An example of interaction with the command line
 * Theoretically, the <a href="https://stefanbirkner.github.io/system-rules/">System Rules</a> could be used instead
 */
class ApplicationTest {
    private final InputStream defaultIn = System.in;
    private final PrintStream defaultOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testMain() {
        String input = "John";
        String expectedOutput = String.format("What is your name? >%nHello, %s!", input);

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Application.main(new String[]{});

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setIn(defaultIn);
        System.setOut(defaultOut);
    }
}