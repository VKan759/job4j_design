package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (findById(model.getId()) == null) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            storage.put(model.getId(), model);
            return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}