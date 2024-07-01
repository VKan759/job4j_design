package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{'J', 'A', 'V', 'A'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.println((char) data);
        }
        int i;
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        ByteArrayInputStream stream1 = new ByteArrayInputStream(bytes1, 1, 15);
        while ((i = stream1.read()) != -1) {
            System.out.println((char) i);
        }

        System.out.println();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        output.writeBytes(bytes2);
        System.out.println(output);

        try (FileOutputStream outputStream = new FileOutputStream("data/message.txt")) {
            output.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
