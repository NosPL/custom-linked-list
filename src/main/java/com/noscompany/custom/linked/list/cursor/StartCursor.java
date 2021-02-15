package com.noscompany.custom.linked.list.cursor;

import java.util.NoSuchElementException;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.*;

class StartCursor<T> extends Cursor<T> {

    StartCursor(Node<T> node, LastOperation lastOperation) {
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
            return new MiddleCursor<>(node.getNextNode(), 1, NEXT);
        else
            return new EndCursor<>(node, 1, NEXT);
    }

    @Override
    public Cursor<T> moveToPrevious() {
        throw new NoSuchElementException();
    }

    @Override
    public void set(T t) {
        if (lastOperation == PREVIOUS)
            node.setElement(t);
        else throw new IllegalStateException();
    }

    @Override
    public Cursor<T> add(T t) {
        node.prepend(t);
        return new MiddleCursor<>(node, 1, ADD);
    }

    @Override
    public Cursor<T> remove() {
        if (lastOperation == PREVIOUS) {
            if (node.hasNext()) {
                node = node.getNextNode();
                node.removePrevious();
                return new StartCursor<>(node, REMOVE);
            } else
                return new EmptyCursor<>(REMOVE);
        } else
            throw new IllegalStateException();
    }

    @Override
    protected void validate(Node<T> node) {
        if (node.hasPrevious())
            throw new IllegalArgumentException("Start cursor node cannot have previous node");
    }
}
