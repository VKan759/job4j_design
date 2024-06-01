package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("1", "2", "3", "4", "5");
        assertThat(list).contains("1", "2", "3", "4", "5")
                .doesNotContain("7", "8")
                .startsWith("1")
                .elements(0).isNotNull()
                .allSatisfy(x -> assertThat(Integer.parseInt(x)).isGreaterThan(0));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("1", "1", "1", "1", "1", "2", "3");
        assertThat(set)
                .isNotEmpty()
                .containsExactly("1", "2", "3")
                .containsAnyOf("1")
                .allSatisfy(x -> assertThat(Integer.parseInt(x)).isLessThan(10));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2", "3", "4");
        assertThat(map).hasSize(4)
                .containsKeys("2", "1")
                .containsValues(0, 1, 2, 3)
                .doesNotContainEntry("5", 15);
    }
}