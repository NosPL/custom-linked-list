package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.Optional;

class Middle<T> extends Cursor<T> {

    public Middle(Node<T> node, int nextIndex) {
        super(node, nextIndex);
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
            return Optional.of(new Middle<>(node.getNextNode(), (nextIndex + 1)));
        else
            return Optional.of(new End<>(node, (nextIndex + 1)));
    }

    @Override
    public Optional<Cursor<T>> moveBackward() {
        Node<T> previousNode = node.getPreviousNode();
        if (previousNode.hasPrevious())
            return Optional.of(new Middle<>(previousNode, (nextIndex - 1)));
        else
            return Optional.of(new Beginning<>(previousNode));
    }

    @Override
    public void set(T t) {

    }

    @Override
    public Cursor<T> add(T t) {
        node.prepend(t);
        return new Middle<>(node, (nextIndex + 1));
    }
}
