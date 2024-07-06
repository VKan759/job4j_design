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
                boolean error = line.startsWith("500") || line.startsWith("400");
                if (error) {
                    if (status != isNotAvailable) {
                        writer.append(line.substring(4)).append(";");
                        status = isNotAvailable;
                    }
                } else {
                    if (status != available) {
                        writer.append(line.substring(4)).append(";").append(System.lineSeparator());
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