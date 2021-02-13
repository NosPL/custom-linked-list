package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.NONE;

public abstract class Cursor<T> {

    protected LastOperation lastOperation;

    protected Node<T> node;
    protected int nextIndex;

    public Cursor(Node<T> node, int nextIndex, LastOperation lastOperation) {
        this.node = node;
        this.nextIndex = nextIndex;
        this.lastOperation = lastOperation;
    }

    public static <T> Cursor<T> beginning(Node<T> node) {
        return new Beginning<>(node, NONE);
    }

    public abstract boolean hasNext();

    public abstract boolean hasPrevious();

    public abstract Cursor<T> moveToNext();

    public abstract Cursor<T> moveToPrevious();

    public Node<T> getNode() {
        return node;
    }

    public abstract void set(T t);

    public abstract Cursor<T> add(T t);

    public static <T> Cursor<T> empty() {
        return new Empty<>(NONE);
    }

    enum LastOperation {PREVIOUS, NEXT, NONE, ADD}

    public int previousIndex() {
        return (nextIndex - 1);
    }

    public int nextIndex() {
        return nextIndex;
    }

    public abstract Cursor<T> remove();
}
