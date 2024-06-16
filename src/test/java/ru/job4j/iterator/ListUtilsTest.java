package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        input.add(2);
        input.add(2);
        input.add(5);
        ListUtils.removeIf(input, x -> x < 3);
        assertThat(input).containsSequence(3, 5);
    }

    @Test
    void whenReplaceIf() {
        input.add(2);
        input.add(2);
        input.add(5);
        ListUtils.replaceIf(input, x -> x < 3, 7);
        assertThat(input).containsSequence(7, 3, 7, 7, 5);
    }

    @Test
    void whenRemoveAll() {
        input.add(2);
        input.add(2);
        input.add(5);
        ListUtils.removeAll(input, List.of(3, 2));
        assertThat(input).containsSequence(1, 5);
    }
}