package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.ADD;
import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.NEXT;

class Beginning<T> extends Cursor<T> {

    Beginning(Node<T> node, LastOperation lastOperation) {
        super(node, 0, lastOperation);
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
            return new Middle<>(node.getNextNode(), 1, NEXT);
        else
            return new End<>(node, 1, NEXT);
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
        return new Middle<>(node, 1, ADD);
    }

    @Override
    public Cursor<T> remove() {
        throw new RuntimeException("not implemented");
    }

    @Override
    protected void validate(Node<T> node) {
        if (node.hasPrevious())
            throw new IllegalArgumentException("Beginning node cannot have previous node");
    }
}
