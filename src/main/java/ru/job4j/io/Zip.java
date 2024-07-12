package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {

        try (ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(target))) {
            int index = sources.get(0).toString().lastIndexOf("\\");
            for (Path source : sources) {
                File file = source.toFile();
                if (file.isDirectory()) {
                    String directoryName = source.toString().substring(index + 1).concat("\\");
                    ZipEntry zipEntry = new ZipEntry(directoryName);
                    outputStream.putNextEntry(zipEntry);
                    continue;
                }
                ZipEntry zipEntry = new ZipEntry(source.toString().substring(index + 1));
                outputStream.putNextEntry(zipEntry);
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    outputStream.write(inputStream.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        List<Path> list = Search.search(Path.of(argsName.get("d")),
                x -> !x.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(list, new File(argsName.get("o")));
    }
}