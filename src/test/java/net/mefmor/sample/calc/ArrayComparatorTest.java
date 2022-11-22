package net.mefmor.sample.calc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayComparatorTest {

    @Test
    void testStreamsFindDuplicates() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 4, 5};
        int[] c = {0, 3, 4, 8, 9};

        Set<Integer> setA = IntStream.of(a).boxed().collect(Collectors.toSet());
        Set<Integer> setB = IntStream.of(b).boxed().collect(Collectors.toSet());
        Set<Integer> setC = IntStream.of(c).boxed().collect(Collectors.toSet());

        int[] result = setA.stream()
                .filter(setB::contains)
                .filter(setC::contains)
                .mapToInt(Integer::intValue)
                .toArray();

        Assertions.assertArrayEquals(new int[]{3, 4}, result);
    }
}
