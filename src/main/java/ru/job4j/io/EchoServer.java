package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    if (string.contains("msg=Exit")) {
                        output.write("Завершить работу сервера".getBytes());
                        server.close();
                    }
                    if (string.contains("msg=")) {
                        output.write(string.substring(string.indexOf("msg=") + 4, string.indexOf(' ',
                                string.indexOf("msg=") + 4)).getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }
}