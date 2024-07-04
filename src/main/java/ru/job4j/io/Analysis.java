package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            LinkedList<String> buffer = new LinkedList<>();
            StringBuilder bufferResult = new StringBuilder();

            reader.lines()
                    .forEach(buffer::add);
            boolean available = true;
            boolean isNotAvailable = false;
            boolean status = !buffer.isEmpty() && !buffer.getFirst().startsWith("500") && !buffer.getFirst().startsWith(
                    "400") ? available : isNotAvailable;

            for (String line : buffer) {
                if (!line.startsWith("400") && !line.startsWith("500")) {
                    if (available != status) {
                        bufferResult.append(line.substring(4)).append(";").append(System.lineSeparator());
                        status = available;
                        continue;
                    }
                }
                if (status != isNotAvailable) {
                    bufferResult.append(line.substring(4)).append(";");
                    status = isNotAvailable;
                }
            }
            writer.write(String.valueOf(bufferResult));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}