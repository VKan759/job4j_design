package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        Arrays.stream(args).forEach(x -> {
            if (!x.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + x + "' does not contain an "
                        + "equal sign");
            }
            if (!x.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + x
                        + "' does not start with a '-' character");
            }
            if (x.charAt(1) == '=') {
                throw new IllegalArgumentException("Error: This argument '" + x + "' does not contain a key");
            }
            int equalsSignIndex = x.indexOf("=");
            if (x.substring(equalsSignIndex + 1).isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + x + "' does not contain a value");
            }

        });
    }

    private void parse(String[] args) {
        validate(args);
        Arrays.stream(args).forEach(x -> {
            String key;
            String value;
            int index = x.indexOf("=");
            key = x.substring(1, index);
            value = x.substring(index + 1);
            values.put(key, value);
        });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}