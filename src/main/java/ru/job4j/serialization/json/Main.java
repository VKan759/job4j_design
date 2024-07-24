package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer(true, 500, "Lenovo Gaming", new Display("FullHD"),
                new String[]{" display", "keyboard", "processor"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(computer));

        final String json = "{"
                + "\"portable\":true,"
                + "\"memoryValue\":1024,"
                + "\"name\":\"ASUS\","
                + "\"display\":"
                + "{"
                + "\"type\":\"OLED\""
                + "},"
                + "\"components\":"
                + "[\"mono block\", \"keyboard\"]"
                + "}";
        final Computer computer1 = gson.fromJson(json, Computer.class);
        System.out.println(computer1);
    }
}
