package com.oop_pub.exceptions.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainEx1 {
    private static void readAndPrintLine() throws IOException {
        // DONE: Read a line from stdin and print it to stdout
        // DONE: Don't forget to close the reader (Hint: try-with-resources, try-finally)

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String message = reader.readLine();
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    public static void main(String[] args) throws IOException {
        readAndPrintLine();
    }
}
