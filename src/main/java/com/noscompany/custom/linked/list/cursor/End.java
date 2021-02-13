package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.ADD;
import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.PREVIOUS;


class End<T> extends Cursor<T> {

    public End(Node<T> node, int nextIndex, LastOperation lastOperation) {
        super(node, nextIndex, lastOperation);
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
    public Cursor<T> moveToNext() {
        Optional.empty().orElseThrow(NoSuchElementException::new);
        return null;
    }

    @Override
    public Cursor<T> moveToPrevious() {
        if (node.hasPrevious())
            return new Middle<>(node, (nextIndex - 1), PREVIOUS);
        else
            return new Beginning<>(node, PREVIOUS);
    }

    @Override
    public void set(T t) {

    }

    @Override
    public Cursor<T> add(T t) {
        node.append(t);
        return new End<>(node.getNextNode(), (nextIndex + 1), ADD);
    }

    @Override
    public Cursor<T> remove() {
        throw new RuntimeException("not implemented");
    }
}
