package ru.job4j.generic;

public class RoleStore implements Store<Role> {

    private final Store<Role> list = new SecMemStore<>();

    @Override
    public void add(Role model) {
        list.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return list.replace(id, model);
    }

    @Override
    public void delete(String id) {
        list.delete(id);
    }

    @Override
    public Role findById(String id) {
        return list.findById(id);
    }
}
