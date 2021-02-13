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

    }

    @Override
    public Cursor<T> add(T t) {
        node.prepend(t);
        return new Middle<>(node, (nextIndex + 1), ADD);
    }

    @Override
    public Cursor<T> remove() {
        if (lastOperation == PREVIOUS) {
            node.removeNext();
            if (node.hasNext())
                return new Middle<>(node, (nextIndex - 1), REMOVE);
            else
                return new End<>(node, (nextIndex - 1), REMOVE);
        } else if (lastOperation == NEXT) {
            node.removePrevious();
            if (node.hasPrevious())
                return new Middle<>(node, (nextIndex - 1), REMOVE);
            else
                return new Beginning<>(node, REMOVE);
        } else
            throw new IllegalStateException();
    }

    @Override
    protected void validate(Node<T> node) {
        if (node.isSingle())
            throw new IllegalArgumentException("Middle node must have previous and nex node");
    }
}
