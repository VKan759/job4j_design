package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void check(@TempDir Path tempDir) throws IOException {
        String source = "data/server.log";
        File target = tempDir.resolve("tempFile.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source, target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            bufferedReader.lines().forEach(stringBuilder::append);
        }
        assertThat("10:57:01;11:02:02;").isEqualTo(stringBuilder.toString());
    }
}