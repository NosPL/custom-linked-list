package com.noscompany.custom.linked.list;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

class CustomIterator<E> implements ListIterator<E> {
    private Cursor<E> cursor;
    private int index;

    private CustomIterator(Cursor<E> cursor, int index) {
        this.cursor = cursor;
        this.index = index;
    }

    public static <T> CustomIterator<T> empty() {
        return new CustomIterator<>(new Empty<>(), 0);
    }

    public static <T> CustomIterator<T> head(Node<T> head) {
        Objects.requireNonNull(head);
        return new CustomIterator<>(new Beginning<>(head), 0);
    }

    @Override
    public boolean hasNext() {
        return cursor.hasNext();
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public E next() {
        E e = cursor.getNode().getElement();
        cursor = cursor.moveForward().orElseThrow(NoSuchElementException::new);
        index++;
        return e;
    }

    @Override
    public void add(E e) {
        cursor = cursor.add(e);
        index++;
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
        cursor = cursor.moveBackward().orElseThrow(NoSuchElementException::new);
        index--;
        return cursor.getNode().getElement();
    }

    @Override
    public void set(E e) {
        cursor.set(e);
    }
}
