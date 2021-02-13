package com.noscompany.custom.linked.list;

import com.noscompany.custom.linked.list.cursor.Cursor;

import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Consumer;

class CustomIterator<E> implements ListIterator<E> {
    private Cursor<E> cursor;

    public CustomIterator(Cursor<E> cursor) {
        this.cursor = cursor;
    }

    public static <T> CustomIterator<T> empty() {
        return new CustomIterator<>(Cursor.empty());
    }

    public static <T> CustomIterator<T> head(Node<T> head) {
        Objects.requireNonNull(head);
        return new CustomIterator<>(Cursor.beginning(head));
    }

    @Override
    public boolean hasNext() {
        return cursor.hasNext();
    }

    @Override
    public int nextIndex() {
        return cursor.nextIndex();
    }

    @Override
    public int previousIndex() {
        return cursor.previousIndex();
    }

    @Override
    public void remove() {
        cursor = cursor.remove();
    }

    @Override
    public E next() {
        E e = cursor.getNode().getElement();
        cursor = cursor.moveToNext();
        return e;
    }

    @Override
    public void add(E e) {
        cursor = cursor.add(e);
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        do {
            action.accept(this.next());
        } while (cursor.hasNext());
    }

    @Override
    public boolean hasPrevious() {
        return cursor.hasPrevious();
    }

    @Override
    public E previous() {
        cursor = cursor.moveToPrevious();
        return cursor.getNode().getElement();
    }

    @Override
    public void set(E e) {
        cursor.set(e);
    }
}
