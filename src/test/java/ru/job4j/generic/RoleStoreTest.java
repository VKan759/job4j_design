package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsVyacheslav() {
        RoleStore store = new RoleStore();
        store.add(new Role("765", "Vyacheslav"));
        Role result = store.findById("765");
        assertThat(result.getRoleDescription()).isEqualTo("Vyacheslav");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("765", "Vyacheslav"));
        Role result = store.findById("12");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleDescriptionIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("765", "Vyacheslav"));
        store.add(new Role("765", "Maxim"));
        Role result = store.findById("765");
        assertThat(result.getRoleDescription()).isEqualTo("Vyacheslav");
    }

    @Test
    void whenReplaceThenRoleDescriptionIsVladimir() {
        RoleStore store = new RoleStore();
        store.add(new Role("765", "Vyacheslav"));
        store.replace("765", new Role("765", "Vladimir"));
        Role result = store.findById("765");
        assertThat(result.getRoleDescription()).isEqualTo("Vladimir");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleDescription() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Vyacheslav"));
        boolean result = store.replace("10", new Role("10", "Dima"));
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleDescriptionIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Vyacheslav"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleDescription()).isEqualTo("Vyacheslav");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Vyacheslav"));
        boolean result = store.replace("1", new Role("1", "Dima"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Vyacheslav"));
        boolean result = store.replace("10", new Role("10", "Dima"));
        assertThat(result).isFalse();
    }

}