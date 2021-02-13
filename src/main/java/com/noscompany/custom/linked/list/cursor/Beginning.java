package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.NoSuchElementException;
import java.util.Optional;

class Beginning<T> extends Cursor<T> {

    Beginning(Node<T> node) {
        super(node, 0);
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
    public Cursor<T> moveToNext() {
        if (node.hasNext())
            return new Middle<>(node.getNextNode(), 1);
        else
            return new End<>(node, 1);
    }

    @Override
    public Cursor<T> moveToPrevious() {
        Optional.empty().orElseThrow(NoSuchElementException::new);
        return null;
    }

    @Override
    public void set(T t) {

    }

    @Override
    public Cursor<T> add(T t) {
        node.prepend(t);
        return new Middle<>(node, 1);
    }

    @Override
    public void remove() {
        throw new RuntimeException("not implemented");
    }
}
