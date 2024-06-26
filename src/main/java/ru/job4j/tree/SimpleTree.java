package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        return findByPredicate(x -> x.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> element = data.peek();
            result = condition.test(element) ? Optional.of(element) : Optional.empty();
            if (condition.test(element)) {
                break;
            }
            data.addAll(element.children);
            data.poll();
        }
        return result;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> element = findBy(parent);
        if (element.isPresent() && findBy(child).isEmpty()) {
            Node<E> newNode = new Node<>(child);
            element.get().children.add(newNode);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> eNode.value.equals(value));
    }
}