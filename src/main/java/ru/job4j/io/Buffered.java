package ru.job4j.io;

import java.io.*;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new FileReader("data/input.txt"));
             BufferedWriter output = new BufferedWriter(new FileWriter("data/output.txt", true))
        ) {
            int index;
            char[] buffer = new char[1024];
            while ((index = input.read(buffer)) != -1) {
                output.write(buffer, 0, index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
