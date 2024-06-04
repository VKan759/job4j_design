package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public class SecMemStore<T extends Base> implements Store<T> {
    private final Map<String, T> list = new HashMap<>();

    @Override
    public void add(T model) {
        if (findById(model.getId()) == null) {
            list.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            list.put(id, model);
            return true;
        }
        return false;
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
