package ru.job4j.serialization.json;

import java.util.Arrays;

public class Computer {
    boolean portable;
    int memoryValue;
    String name;
    Display display;
    String[] components;

    public Computer(boolean portable, int memoryValue, String name, Display display, String[] components) {
        this.portable = portable;
        this.memoryValue = memoryValue;
        this.name = name;
        this.display = display;
        this.components = components;
    }

    @Override
    public String toString() {
        return "Computer{"
                + "portable=" + portable
                + ", memoryValue=" + memoryValue
                + ", name='" + name + '\''
                + ", display=" + display
                + ", components=" + Arrays.toString(components)
                + '}';
    }
}
