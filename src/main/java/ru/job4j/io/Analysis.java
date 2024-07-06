package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean status = true;

            while (reader.ready()) {
                line = reader.readLine();
                boolean error = line.startsWith("500") || line.startsWith("400");
                if (status == error) {
                    writer.append(line.substring(4)).append(";").append(status ? "" : System.lineSeparator());
                    status = !status;
                }
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