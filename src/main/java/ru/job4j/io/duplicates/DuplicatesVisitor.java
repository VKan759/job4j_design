package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        attrs = Files.readAttributes(file, BasicFileAttributes.class);
        FileProperty property = new FileProperty(attrs.size(), file.getFileName().toString());
        map.computeIfAbsent(property, x -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void getResult() {
        map.entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .forEach(x -> x.getValue().forEach(System.out::println));
    }
}
