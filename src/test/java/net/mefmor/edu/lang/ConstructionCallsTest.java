package net.mefmor.edu.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ConstructionCallsTest {
    private static final List<String> JOURNAL = new ArrayList<>();

    private static class ParentTestClass {
        {
            JOURNAL.add("Parent non-static initializer");
        }

        static {
            JOURNAL.add("Parent Static initializer");
        }

        ParentTestClass() {
            JOURNAL.add("Parent Constructor");
        }
    }
    private static class TestClass extends ParentTestClass {
        {
            JOURNAL.add("Child non-static initializer");
        }

        static {
            JOURNAL.add("Child static initializer");
        }

        TestClass() {
            JOURNAL.add("Child constructor");
        }
    }

    @Test
    void testSequence() {
        final List<String> expectedResult = Arrays.asList(
                "Parent Static initializer",
                "Child static initializer",
                "Parent non-static initializer",
                "Parent Constructor",
                "Child non-static initializer",
                "Child constructor");

        new TestClass();

        Assertions.assertEquals(expectedResult, JOURNAL);
    }
}
