package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public class SecMemStore<T extends Base> implements Store<T> {
    private final Map<String, T> list = new HashMap<>();

    @Override
    public void add(T model) {
        list.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return list.replace(id, list.get(id), model);
    }

    @Override
    public void delete(String id) {
        list.remove(id);
    }

    @Override
    public T findById(String id) {
        return list.get(id);
    }
}
