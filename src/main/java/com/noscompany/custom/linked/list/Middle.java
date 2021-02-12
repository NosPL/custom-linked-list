package com.noscompany.custom.linked.list;

import java.util.Optional;

public class Middle<T> extends Cursor<T> {

    public Middle(Node<T> node) {
        super(node);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public boolean hasPrevious() {
        return true;
    }

    @Override
    public Optional<Cursor<T>> moveForward() {
        if (node.hasNext())
            return Optional.of(new Middle<>(node.getNextNode()));
        else
            return Optional.of(new End<>(node));
    }

    @Override
    public Optional<Cursor<T>> moveBackward() {
        Node<T> previousNode = node.getPreviousNode();
        if (previousNode.hasPrevious())
            return Optional.of(new Middle<>(previousNode));
        else
            return Optional.of(new Beginning<>(previousNode));
    }

    @Override
    public void set(T t) {

    }

    @Override
    public Cursor<T> add(T t) {
        node.prepend(t);
        return new Middle<>(node);
    }
}
