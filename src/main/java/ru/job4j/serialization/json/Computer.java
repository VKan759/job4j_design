package ru.job4j.serialization.json;

import java.util.Arrays;

import org.json.*;

public class Computer {
    boolean portable;
    int memoryValue;
    String name;
    Display display;
    String[] components;

    public boolean isPortable() {
        return portable;
    }

    public int getMemoryValue() {
        return memoryValue;
    }

    public String getName() {
        return name;
    }

    public Display getDisplay() {
        return display;
    }

    public String[] getComponents() {
        return components;
    }

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

    public static void main(String[] args) {
        Computer computer = new Computer(true, 512, "Lenovo", new Display("UHD"), new String[]{"Notebook"});
        JSONObject jsonObject = new JSONObject(computer);
        JSONObject secondObject = new JSONObject();
        secondObject.put("portable", computer.isPortable());
        secondObject.put("memoryValue", computer.getMemoryValue());
        secondObject.put("name", computer.getName());
        secondObject.put("display", computer.getDisplay());
        secondObject.put("components", computer.getComponents());
        System.out.println(new JSONObject(computer));
        System.out.println(secondObject);
    }
}
