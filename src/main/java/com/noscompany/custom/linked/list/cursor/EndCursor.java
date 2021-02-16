package com.noscompany.custom.linked.list.cursor;

import java.util.NoSuchElementException;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.*;


class EndCursor<T> extends Cursor<T> {

    EndCursor(Node<T> node, int nextIndex, LastOperation lastOperation) {
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
    public Cursor<T> next() {
        throw new NoSuchElementException();
    }

    @Override
    public Cursor<T> previous() {
        if (node.hasPrevious())
            return new MiddleCursor<>(node, (nextIndex - 1), PREVIOUS);
        else
            return new StartCursor<>(node, PREVIOUS);
    }

    @Override
    public void set(T t) {
        if (lastOperation == NEXT)
            node.setElement(t);
        else
            throw new IllegalStateException();
    }

    @Override
    public Cursor<T> add(T t) {
        node.append(t);
        return new EndCursor<>(node.getNextNode(), (nextIndex + 1), ADD);
    }

    @Override
    public Cursor<T> remove() {
        if (lastOperation == NEXT) {
            if (node.hasPrevious()) {
                node = node.getPreviousNode();
                node.removeNext();
                new EndCursor<>(node, (nextIndex - 1), REMOVE);
            } else
                return new EmptyCursor<>(REMOVE);
        }
        throw new IllegalStateException();
    }

    @Override
    protected void validate(Node<T> node) {
        if (node.hasNext())
            throw new IllegalArgumentException("End cursor node cannot have next node");
    }
}
