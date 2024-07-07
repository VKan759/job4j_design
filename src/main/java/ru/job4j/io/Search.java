package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args, String type) throws IOException {
        validate(args, type);
        Path start = Paths.get(".");
        search(start, path -> path.toFile().getName().endsWith(type)).forEach(System.out::println);
    }

    public static void validate(String[] args, String type) throws IllegalArgumentException {
        if (args.length == 0 || args[0].endsWith(type)) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        for (String string : args) {
            if (!string.endsWith(type)) {
                throw new IllegalArgumentException("Incorrect type of file");
            }
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}