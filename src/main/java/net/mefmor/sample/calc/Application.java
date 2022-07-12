package net.mefmor.sample.calc;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    final static String ENTER_NUMERIC_VALUE = "Enter a numeric value: ";
    final static String NOT_NUMBER = "Couldn't format as a number";
    final static String SELECT_OPERATION = "Select an operation (+ - * /): ";
    final static String UNSUPPORTED_OPERATION = "Unsupported operation";
    final static List<String> SUPPORTED_OPERATIONS = Arrays.asList("+", "-", "*", "/");

    private final Scanner sc;

    public static void main(String[] args) {
        Application application = new Application();
        int number1;
        int number2;

        try {
            number1 = application.readNumber();
            number2 = application.readNumber();
        } catch (InputMismatchException e) {
            System.err.print(NOT_NUMBER);
            return;
        }

        String operation = application.readOperation();

        if (SUPPORTED_OPERATIONS.contains(operation)) {
            float result = Application.calculate(number1, number2, operation);

            System.out.print(resultString(number1, number2, operation, result));
        } else {
            System.err.print(UNSUPPORTED_OPERATION);
        }

    }

    private Application() {
        this.sc = new Scanner(System.in);
    }

    static String resultString(int num1, int num2, String operation, float result) {
        return String.format("%d %s %d = %.1f", num1, operation, num2, result);
    }

    static float calculate(int number1, int number2, String operation) {
        return switch (operation) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> (float) number1 / number2;
            default -> throw new IllegalArgumentException("Unsupported operation type " + operation);
        };
    }

    private int readNumber() {
        System.out.print(ENTER_NUMERIC_VALUE);
        return sc.nextInt();
    }

    private String readOperation() {
        System.out.print(SELECT_OPERATION);
        return sc.next();
    }
}
