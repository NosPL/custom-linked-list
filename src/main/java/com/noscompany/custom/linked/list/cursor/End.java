package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.Optional;


class End<T> extends Cursor<T> {

    public End(Node<T> node, int nextIndex) {
        super(node, nextIndex);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return true;
    }

    @Override
    public Optional<Cursor<T>> moveForward() {
        return Optional.empty();
    }

    @Override
    public Optional<Cursor<T>> moveBackward() {
        if (node.hasPrevious())
            return Optional.of(new Middle<>(node, (nextIndex - 1)));
        else
            return Optional.of(new Beginning<>(node));
    }

    @Override
    public void set(T t) {

    }

    @Override
    public Cursor<T> add(T t) {
        node.append(t);
        return new End<>(node.getNextNode(), (nextIndex + 1));
    }
}
