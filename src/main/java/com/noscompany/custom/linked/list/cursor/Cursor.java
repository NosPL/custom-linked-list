package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

public abstract class Cursor<T> {
    protected Node<T> node;
    protected int nextIndex;

    public Cursor(Node<T> node, int nextIndex) {
        this.node = node;
        this.nextIndex = nextIndex;
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

    public static <T> Cursor<T> beginning(Node<T> node) {
        return new Beginning<>(node);
    }

    public static <T> Cursor<T> empty() {
        return new Empty<>();
    }

    public int previousIndex() {
        return (nextIndex - 1);
    }

    public int nextIndex() {
        return nextIndex;
    }
}
