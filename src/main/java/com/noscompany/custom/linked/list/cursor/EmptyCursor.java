package com.noscompany.custom.linked.list.cursor;

import java.util.NoSuchElementException;

import static com.noscompany.custom.linked.list.cursor.Cursor.LastOperation.ADD;

class EmptyCursor<T> extends Cursor<T> {

    EmptyCursor(LastOperation lastOperation) {
        super(null, 0, lastOperation);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Cursor<T> moveToNext() {
        throw new NoSuchElementException();
    }

    @Override
    public Cursor<T> moveToPrevious() {
        throw new NoSuchElementException();
    }

    @Override
    public T getElement() {
        throw new NoSuchElementException();
    }

    @Override
    public void set(T t) {
        throw new IllegalStateException();
    }

    @Override
    public Cursor<T> add(T t) {
        return new EndCursor<>(Node.single(t), 1, ADD);
    }

    @Override
    public Cursor<T> remove() {
        throw new IllegalStateException();
    }

    @Override
    protected void validate(Node<T> node) {
    }
}
