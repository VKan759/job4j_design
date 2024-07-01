package ru.job4j.io;

import java.io.*;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"));
             PrintWriter writer = new PrintWriter("data/write.txt")) {
            stream.println("Сообщение printLn");
            stream.write("Из PrintStream в FileOutputStream".getBytes());
            stream.write("Новое сообщение".getBytes());
            writer.write("Новое сообщение");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("data/output.txt"))) {
            writer.println("Hello, world!");
            writer.println("This is an example without autoFlush.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
