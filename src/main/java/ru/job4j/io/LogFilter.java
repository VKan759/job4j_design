package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            input.lines().filter(
                    x -> {
                        boolean lineIsNotEmpty = false;
                        List<String> list = List.of(x.split(" "));
                        if (list.size() >= 2) {
                            Object[] array =  list.toArray();
                            lineIsNotEmpty = array[array.length - 2].equals("404");
                        }
                        return lineIsNotEmpty;
                    }
            ).forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            for (String s : data) {
                output.printf("%s%n", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");

    }
}