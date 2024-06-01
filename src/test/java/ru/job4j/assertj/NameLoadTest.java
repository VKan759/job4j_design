package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void notContainEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("namename"))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .contains("does not contain the symbol '='");
    }

    @Test
    void notContainKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key", "=value");
    }

    @Test
    void notContainsValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value", "key=");
    }

    @Test
    void argumentIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty()
                .contains("empty");
    }

}