package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        int added = 0;
        Info result = new Info(added, changed, deleted);
        Set<User> copyCurrent = new TreeSet<>(Comparator.comparingInt(User::getId));
        copyCurrent.addAll(current);
        Set<User> copyPrevious = new TreeSet<>(Comparator.comparingInt(User::getId));
        copyPrevious.addAll(previous);
        copyCurrent.removeAll(previous);
        copyPrevious.removeAll(current);

        if (!copyCurrent.isEmpty()) {
            Iterator<User> currentIterator = copyCurrent.iterator();
            User currentUser = currentIterator.next();
            for (User previousUser : copyPrevious) {
                if (previousUser.getId() < currentUser.getId()) {
                    continue;
                }
                if (previousUser.getId() == currentUser.getId()
                        && !previousUser.getName().equals(currentUser.getName())
                ) {
                    changed++;
                }
                if (currentIterator.hasNext()) {
                    currentUser = currentIterator.next();
                } else {
                    break;
                }
            }
        }

        deleted = !copyCurrent.isEmpty() ? copyPrevious.size() - changed : copyPrevious.size();
        added = copyCurrent.size() - changed;

        result.setChanged(changed);
        result.setDeleted(deleted);
        result.setAdded(added);

        return result;
    }
}