package com.noscompany.custom.linked.list;

import com.noscompany.custom.linked.list.cursor.Cursor;

import java.util.ListIterator;
import java.util.function.Consumer;

class CustomIterator<E> implements ListIterator<E> {
    private Cursor<E> cursor;
    private int size;

    private CustomIterator(Cursor<E> cursor, int size) {
        this.cursor = cursor;
        this.size = size;
    }

    public static <T> CustomIterator<T> empty() {
        return new CustomIterator<>(Cursor.empty(), 0);
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
        size--;
    }

    @Override
    public E next() {
        E e = cursor.getElement();
        cursor = cursor.moveToNext();
        return e;
    }

    @Override
    public void add(E e) {
        cursor = cursor.add(e);
        size++;
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        while (cursor.hasNext())
            action.accept(this.next());
    }

    @Override
    public boolean hasPrevious() {
        return cursor.hasPrevious();
    }

    @Override
    public E previous() {
        cursor = cursor.moveToPrevious();
        return cursor.getElement();
    }

    @Override
    public void set(E e) {
        cursor.set(e);
    }

    public void moveAtTheBeginning() {
        while (hasPrevious())
            previous();
    }

    public void moveAtTheEnd() {
        while (hasNext())
            next();
    }

    public int size() {
        return size;
    }

    public void resetLastOperation() {
        cursor.resetLastOperation();
    }
}
