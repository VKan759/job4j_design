package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;

    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().filter(x -> !x.contains("#") && !x.isEmpty()).forEach(
                    x -> {
                        String[] keyValue = List.of(x.split("=")).toArray(new String[]{});
                        if (keyValue.length < 2
                                || keyValue[0].isEmpty()
                        ) {
                            throw new IllegalArgumentException();
                        }

                        char[] array = x.toCharArray();
                        int indexValue = 0;
                        for (char c : array) {
                            if (c == '=') {
                                break;
                            }
                            indexValue++;
                        }
                        String key = x.substring(0, indexValue);
                        String value = x.substring(indexValue + 1);
                        values.put(key, value);
                    }
            );
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        load();
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/ap2p.properties"));
    }
}