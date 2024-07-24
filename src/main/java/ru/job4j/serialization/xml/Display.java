package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "display")
public class Display {
    @XmlAttribute
    String type;

    public Display() {
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
