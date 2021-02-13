package com.noscompany.custom.linked.list.cursor;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.NONE;

public abstract class Cursor<T> {

    protected enum LastOperation {PREVIOUS, NEXT, NONE, ADD, REMOVE}

    protected LastOperation lastOperation;
    protected Node<T> node;
    protected int nextIndex;

    protected Cursor(Node<T> node, int nextIndex, LastOperation lastOperation) {
        validate(node);
        this.node = node;
        this.nextIndex = nextIndex;
        this.lastOperation = lastOperation;
    }

    public static <T> Cursor<T> empty() {
        return new EmptyCursor<>(NONE);
    }

    public abstract boolean hasNext();

    public abstract boolean hasPrevious();

    public abstract Cursor<T> moveToNext();

    public abstract Cursor<T> moveToPrevious();

    public T getElement() {
        return node.getElement();
    }

    public abstract void set(T t);

    public abstract Cursor<T> add(T t);

    public int previousIndex() {
        return (nextIndex - 1);
    }

    public int nextIndex() {
        return nextIndex;
    }

    public abstract Cursor<T> remove();

    protected abstract void validate(Node<T> node);

    public void resetLastOperation() {
        lastOperation = NONE;
    }
}