package ru.job4j.console;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    private List<String> log = new LinkedList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String string = "";
        while (!string.equalsIgnoreCase(OUT)) {
            string = scanner.nextLine();
            if (string.equalsIgnoreCase(STOP)) {
                log.add(STOP.concat(System.lineSeparator()));
                while (!string.equalsIgnoreCase(CONTINUE)) {
                    string = scanner.nextLine();
                }
            }
            if (!string.equalsIgnoreCase(OUT)) {
                log.add(string.concat(System.lineSeparator()));
                String answer = readPhrases().get((int) (readPhrases().size() * Math.random()));
                System.out.println(answer);
                log.add(answer.concat(System.lineSeparator()));
            }
        }
        log.add(OUT);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            list = bufferedReader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            log.forEach(x -> {
                try {
                    writer.write(x);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/log.txt", "data/answers.txt");
        consoleChat.run();
    }
}
