package net.mefmor.edu.lang;

import java.util.Random;

public class StreamSample {

    public static void main(String[] args) {
        (new Random())
                .ints()
                .limit(10)
                .forEach(System.out::println);
    }
}
