package net.mefmor.sample.calc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ApplicationTest {
    private final InputStream defaultIn = System.in;
    private final PrintStream defaultOut = System.out;

    private final PrintStream defaultError = System.err;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @Test
    void testAddition() {
        String input = prepareInput(2, 3, "+");
        String expectedOutput = prepareOutput(2, 3, "+", 5f);

        setInput(input);
        Application.main(new String[]{});

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testDivision() {
        String input = prepareInput(12, 4, "/");
        String expectedOutput = prepareOutput(12, 4, "/", 3f);

        setInput(input);
        Application.main(new String[]{});

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testInvalidNumber() {
        String input = String.format("%s%n", "xyz");

        setInput(input);
        Application.main(new String[]{});

        Assertions.assertEquals(Application.NOT_NUMBER, errorStream.toString());
    }

    @Test
    void testUnsupportedOperation() {
        String input = String.format("2%n3%n%s", "xyz");

        setInput(input);
        Application.main(new String[]{});

        Assertions.assertEquals(Application.UNSUPPORTED_OPERATION, errorStream.toString());
    }

    private String prepareInput(int num1, int num2, String operation) {
        return String.format("%d%n%d%n%s%n", num1, num2, operation);
    }

    private String prepareOutput(int num1, int num2, String operation, float result) {
        return String.format("%s%s%s%s",
                Application.ENTER_NUMERIC_VALUE,
                Application.ENTER_NUMERIC_VALUE,
                Application.SELECT_OPERATION,
                Application.resultString(num1, num2, operation, result));
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setIn(defaultIn);
        System.setOut(defaultOut);
        System.setErr(defaultError);
    }
}