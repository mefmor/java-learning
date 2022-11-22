package net.mefmor.edu.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    void testStringPool() {
        var s1 = "String";
        var s2 = "String";
        var s3 = "Str" + "ing";
        var s4 = "Str";
        var s5 = s4 + "ing";

        Assertions.assertSame(s1, s2);

        Assertions.assertSame(s1, s3);
        Assertions.assertSame(s2, s3);

        Assertions.assertNotSame(s1, s5);
        Assertions.assertNotSame(s2, s5);

        Assertions.assertSame(s1, s5.intern());
        Assertions.assertSame(s2, s5.intern());
    }

}
