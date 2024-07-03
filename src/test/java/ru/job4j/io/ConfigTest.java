package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenEmptyLinesAndWithComments() {
        String path = "./data/with_empty_lines_and_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenKeyIsAbsent() {
        String path = "./data/without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueIsAbsent() {
        String path = "./data/without_values.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEqualsCharIsAbsent() {
        String path = "./data/without_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueContainsEqualsChar() {
        String path = "./data/with_empty_lines_and_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver=");
    }

    @Test
    void whenValueContainsEqualsCharAndValue() {
        String path = "./data/with_empty_lines_and_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres=1");
    }
}