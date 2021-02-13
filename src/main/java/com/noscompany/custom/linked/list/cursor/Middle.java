package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.*;

class Middle<T> extends Cursor<T> {

    public Middle(Node<T> node, int nextIndex, LastOperation lastOperation) {
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
    public Cursor<T> moveToNext() {
        if (node.hasNext())
            return new Middle<>(node.getNextNode(), (nextIndex + 1), NEXT);
        else
            return new End<>(node, (nextIndex + 1), NEXT);
    }

    @Override
    public Cursor<T> moveToPrevious() {
        Node<T> previousNode = node.getPreviousNode();
        if (previousNode.hasPrevious())
            return new Middle<>(previousNode, (nextIndex - 1), PREVIOUS);
        else
            return new Beginning<>(previousNode, PREVIOUS);
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
        return new Middle<>(node, (nextIndex + 1), ADD);
    }

    @Override
    public Cursor<T> remove() {
        if (lastOperation == PREVIOUS) {
            return removeNext();
        } else if (lastOperation == NEXT) {
            return removePrevious();
        } else
            throw new IllegalStateException();
    }

    private Cursor<T> removeNext() {
        if (node.hasNext()) {
            node = node.getNextNode();
            node.removePrevious();
            return new Middle<>(node, nextIndex, REMOVE);
        } else {
            node = node.getNextNode();
            node.removePrevious();
            return new End<>(node, nextIndex, REMOVE);
        }
    }

    private Cursor<T> removePrevious() {
        if (node.getPreviousNode().hasPrevious()) {
            node.removePrevious();
            return new Middle<>(node, (nextIndex - 1), REMOVE);
        } else {
            node.removePrevious();
            return new Beginning<>(node, REMOVE);
        }
    }

    @Override
    protected void validate(Node<T> node) {
        if (node.isSingle())
            throw new IllegalArgumentException("Middle cursor node must have previous and nex node");
    }
}
