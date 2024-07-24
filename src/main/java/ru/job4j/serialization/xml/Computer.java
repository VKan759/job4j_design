package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "computer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {
    @XmlAttribute
    boolean portable;
    @XmlAttribute
    int memoryValue;
    @XmlAttribute
    String name;
    Display display;

    @XmlElementWrapper(name = "components")
    @XmlElement(name = "component")
    String[] components;

    public Computer() {
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

    public static void main(String[] args) throws Exception {
        Computer computer = new Computer(true, 512, "Lenovo ThinkPad", new Display("Amoled"),
                new String[]{"Display", "Processor", "keyboard"});

        JAXBContext context = JAXBContext.newInstance(Computer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(computer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Computer result = (Computer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}