package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(".");
        search(start, path -> path.toFile().getName().endsWith(".txt")).forEach(System.out::println);
    }

    public static void validate(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("В параметрах не указаны файл/расширение");
        }
        if (!Files.exists(Path.of(args[0]))) {
            throw new IllegalArgumentException("Файл не существует");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Не указано расширение файла");
        }

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}