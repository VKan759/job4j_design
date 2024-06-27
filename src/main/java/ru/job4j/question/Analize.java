package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);
        Set<User> copyCurrent = new HashSet<>(current);
        Set<User> copyPrevious = new HashSet<>(previous);
        int deleted;
        int changed = 0;
        int added;

        copyCurrent.removeAll(previous);
        copyPrevious.removeAll(current);

        for (User currentUser : copyCurrent) {
            for (User previousUser : copyPrevious) {
                if (currentUser.getId() == previousUser.getId() && !currentUser.getName().equals(previousUser.getName())) {
                    changed++;
                }
            }
        }

        added = copyCurrent.size() - changed;
        deleted = copyPrevious.size() - changed;

        result.setChanged(changed);
        result.setDeleted(deleted);
        result.setAdded(added);

        return result;
    }
}