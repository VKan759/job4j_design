package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            int read;
            StringBuilder lines = new StringBuilder();
            while ((read = input.read()) != -1) {
                lines.append((char) read);
            }
            String[] text = lines.toString().split(System.lineSeparator());
            for (String value : text) {
                String result = Integer.parseInt(value) % 2 == 0 ? "четное число" : "нечетное число";
                System.out.println(value + " - " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
