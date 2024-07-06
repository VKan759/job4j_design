package ru.job4j.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("path");
        /*Files.createDirectories(path);
        Path file = Path.of("path/path.txt");
        Files.createFile(file);
        Path secondPath = Path.of("secondPath");
        Files.createDirectories(secondPath);*/
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
    stream.forEach(System.out::println);

    Path path2 = Path.of("Attributes.txt");
/*Files.createFile(path2);*/
        BasicFileAttributes fileAttributes = Files.readAttributes(path2, BasicFileAttributes.class);
        System.out.println("Это обычный файл? " + fileAttributes.isRegularFile());
        System.out.println("Время создания " + fileAttributes.creationTime());
        System.out.println("Это директория? " + fileAttributes.isDirectory());
        System.out.println("последнее время доступа " + fileAttributes.lastAccessTime());
        System.out.println("Время последней модификации " + fileAttributes.lastModifiedTime());
        System.out.println(fileAttributes.getClass());


    }
}
