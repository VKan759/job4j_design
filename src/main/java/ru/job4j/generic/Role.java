package ru.job4j.generic;

public class Role extends Base {

    private final String roleDescription;

    public Role(String id, String roleDescription) {
        super(id);
        this.roleDescription = roleDescription;
    }

    public String getRoleDescription() {
        return roleDescription;
    }
}
