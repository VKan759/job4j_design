package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    private static void validate(ArgsName argsName) {
        if (!new File(argsName.get("path")).exists()) {
            throw new IllegalArgumentException("Ошибка: файл не существует");
        }
        if (argsName.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Ошибка: Не указан разделитель");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Ошибка: не указан фильтр для сортировки");
        }
    }

    public static void handle(ArgsName argsName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(argsName.get("path")));
             BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out")))) {
            String delimiter = argsName.get("delimiter");

            String[] filter = argsName.get("filter").split(",");
            List<String> inputLine = Arrays.stream(scanner.nextLine().split(delimiter)).toList();
            List<Integer> indexList = new ArrayList<>();

            for (String filterName : filter) {
                indexList.add(inputLine.indexOf(filterName));
            }

            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(String.join(delimiter, filter));
                while (scanner.hasNextLine()) {
                    String[] finalLine = scanner.nextLine().split(delimiter);
                    List<String> lineToWrite = indexList.stream().map(x -> finalLine[x]).toList();
                    System.out.println(String.join(delimiter, lineToWrite));
                }
            } else {
                writer.write(String.join(delimiter, filter) + System.lineSeparator());
                while (scanner.hasNextLine()) {
                    String[] finalLine = scanner.nextLine().split(delimiter);
                    List<String> lineToWrite = indexList.stream().map(x -> finalLine[x]).toList();
                    writer.write(String.join(delimiter, lineToWrite) + System.lineSeparator());
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {
        validate(ArgsName.of(args));
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}