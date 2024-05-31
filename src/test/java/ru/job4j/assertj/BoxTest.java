package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .contains("Sph");
    }

    @Test
    void numberOfVertexI10() {
        Box box = new Box(4, 15);
        assertThat(box.getNumberOfVertices())
                .isEqualTo(4)
                .isLessThan(20)
                .isEven();
    }

    @Test
    void isExist() {
        Box box = new Box(0, 10);
        assertThat(box.isExist()).isEqualTo(true);
    }

    @Test
    void getAreaIs96() {
        Box box = new Box(8, 4);
        double square = box.getArea();
        assertThat(square)
                .isPositive()
                .isGreaterThan(20D)
                .isCloseTo(96D, withPrecision(0.01D));
    }
}