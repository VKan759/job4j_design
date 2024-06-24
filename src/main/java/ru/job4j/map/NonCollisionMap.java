package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];


    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        int hash = hash(Objects.hashCode(key));
        int index = indexFor(hash);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode == 65536 ? 65537 : hashCode % 65536;
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> element : table) {
            if (element != null) {
                int hash = hash(Objects.hashCode(element.key));
                int index = indexFor(hash);
                newTable[index] = element;
            }
        }
        table = newTable;
        modCount++;
    }

    @Override
    public V get(K key) {
        int hash = hash(Objects.hashCode(key));
        int index = indexFor(hash);
        MapEntry<K, V> element = table[index];
        return element != null
                && Objects.hashCode(key) == Objects.hashCode(element.key)
                && Objects.equals(key, element.key)
                ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hash = hash(Objects.hashCode(key));
        int index = indexFor(hash);
        MapEntry<K, V> element = table[index];
        if (element != null
                && Objects.hashCode(key) == Objects.hashCode(element.key)
                && Objects.equals(key, element.key)) {
            table[index] = null;
            modCount--;
            count--;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int index;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}