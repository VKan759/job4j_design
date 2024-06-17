package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        iterator.add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listIterator = list.listIterator();
        for (int i = 0; i <= index; i++) {
            listIterator.next();
        }
        listIterator.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
                listIterator.add(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        removeIf(list, x -> {
                    boolean result = false;
                    ListIterator<T> listIterator = elements.listIterator();
                    T value;
                    for (int i = 0; i < elements.size(); i++) {
                        value = listIterator.next();
                        if (value.equals(x)) {
                            result = true;
                            break;
                        }
                    }
                    return result;
                }
        );
    }
}