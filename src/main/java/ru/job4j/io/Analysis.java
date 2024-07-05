package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {

            String line = reader.readLine();
            boolean available = true;
            boolean isNotAvailable = false;
            boolean status = !line.isEmpty() && !line.startsWith("500") && !line.startsWith(
                    "400") ? available : isNotAvailable;
            while (line != null) {
                if (line.startsWith("400") || line.startsWith("500")) {
                    if (status != isNotAvailable) {
                        writer.write(line.substring(4) + ";");
                        status = isNotAvailable;
                    }
                } else {
                    if (status != available) {
                        writer.write(line.substring(4) + ";" + System.lineSeparator());
                        status = available;
                    }
                }
                line = reader.readLine();
            }
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