package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.Optional;

public abstract class Cursor<T> {
    protected Node<T> node;

    public Cursor(Node<T> node) {
        this.node = node;
    }

    public abstract boolean hasNext();

    public abstract boolean hasPrevious();

    public abstract Optional<Cursor<T>> moveForward();

    public abstract Optional<Cursor<T>> moveBackward();

    public Node<T> getNode() {
        return node;
    }

    public abstract void set(T t);

    public abstract Cursor<T> add(T t);
}
