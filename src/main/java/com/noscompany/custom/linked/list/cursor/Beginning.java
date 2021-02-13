package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.Optional;

class Beginning<T> extends Cursor<T> {

    Beginning(Node<T> node) {
        super(node);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public boolean hasPrevious() {
        return false;
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
        return Optional.empty();
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
