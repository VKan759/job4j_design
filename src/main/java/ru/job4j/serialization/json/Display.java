package ru.job4j.serialization.json;

public class Display {
    String type;

    public String getType() {
        return type;
    }

    public Display(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Display{"
                + "type='" + type + '\''
                + '}';
    }
}
