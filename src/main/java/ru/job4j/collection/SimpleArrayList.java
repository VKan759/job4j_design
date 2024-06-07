package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;


    public SimpleArrayList(int capacity) {

        container = (T[]) new Object[Math.max(capacity, 10)];
    }

    private T[] increaseCapacity() {
        return Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        container[size] = value;
        size++;
        if (size == container.length) {
            container = increaseCapacity();
        }
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T temp = container[index];
        Objects.checkIndex(index, size);
        container[index] = newValue;
        return temp;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T temp = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - 1 - index);
        container[container.length - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        Integer expectedModCount = modCount;

        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (index > size) {
                    throw new NoSuchElementException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}