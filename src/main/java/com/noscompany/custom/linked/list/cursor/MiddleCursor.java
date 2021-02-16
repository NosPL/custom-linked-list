package com.noscompany.custom.linked.list.cursor;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.*;

class MiddleCursor<T> extends Cursor<T> {

    MiddleCursor(Node<T> node, int nextIndex, LastOperation lastOperation) {
        super(node, nextIndex, lastOperation);
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
    public Cursor<T> next() {
        if (node.hasNext())
            return new MiddleCursor<>(node.getNextNode(), (nextIndex + 1), NEXT);
        else
            return new EndCursor<>(node, (nextIndex + 1), NEXT);
    }

    @Override
    public Cursor<T> previous() {
        Node<T> previousNode = node.getPreviousNode();
        if (previousNode.hasPrevious())
            return new MiddleCursor<>(previousNode, (nextIndex - 1), PREVIOUS);
        else
            return new StartCursor<>(previousNode, PREVIOUS);
    }

    @Override
    public void set(T t) {
        if (lastOperation == NEXT) {
            node.getPreviousNode().setElement(t);
        } else if (lastOperation == PREVIOUS) {
            node.setElement(t);
        } else
            throw new IllegalStateException();
    }

    @Override
    public Cursor<T> add(T t) {
        node.prepend(t);
        return new MiddleCursor<>(node, (nextIndex + 1), ADD);
    }

    @Override
    protected Cursor<T> removeNext() {
        node = node.getPreviousNode();
        node.removeNext();
        if (node.hasNext())
            return new MiddleCursor<>(node.getNextNode(), nextIndex, REMOVE);
        else
            return new EndCursor<>(node, nextIndex, REMOVE);
    }

    @Override
    protected Cursor<T> removePrevious() {
        node.removePrevious();
        if (node.hasPrevious())
            return new MiddleCursor<>(node, (nextIndex - 1), REMOVE);
        else
            return new StartCursor<>(node, REMOVE);
    }

    @Override
    protected void validate(Node<T> node) {
        if (!node.hasPrevious())
            throw new IllegalArgumentException("Middle cursor node must have previous node");
    }
}
