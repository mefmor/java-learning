package net.mefmor.sample.hello;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("What is your name? >");
        String name = sc.nextLine();
        System.out.printf("%nHello, %s!", name);
    }
}
