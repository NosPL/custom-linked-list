package com.noscompany.custom.linked.list;

import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

class CustomIterator<E> implements ListIterator<E> {
    private Node<E> current;
    private int index;

    private CustomIterator(Node<E> current, int index) {
        this.current = current;
        this.index = index;
    }

    public static <T> CustomIterator<T> head(Node<T> head) {
        return new CustomIterator<>(head, -1);
    }

    public static <T> CustomIterator<T> tail(Node<T> tail, int indexOfLast) {
        return new CustomIterator<>(tail, indexOfLast);
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E e = current.getElement();
        current = current.getNextNode();
        index++;
        return e;
    }

    @Override
    public boolean hasPrevious() {
        return current.hasPrevious();
    }

    @Override
    public E previous() {
        current = current.getPreviousNode();
        index--;
        return current.getElement();
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public int previousIndex() {
        return index;
    }

    public Node<E> nextNode() {
        Node<E> nextNode = current.getNextNode();
        current = current.getNextNode();
        index++;
        return nextNode;
    }

    @Override
    public void remove() {
        if (current.hasPrevious())
            current.removePreviousNode();
        else
            throw new IllegalStateException();
    }

    @Override
    public void set(E e) {

    }

    @Override
    public void add(E e) {

    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        do {
            action.accept(this.next());
        } while (current.hasNext());
    }

    public int getIndex() {
        return index;
    }

    public Optional<IteratorEntry<E>> iterateAndBreakOn(Predicate<IteratorEntry<E>> predicate) {
        while (hasNext()) {
            IteratorEntry<E> tuple = new IteratorEntry<>(index, nextNode());
            if (predicate.test(tuple))
                return Optional.of(tuple);
        }
        return Optional.empty();
    }

    CustomIterator<E> customIterator(int index) {
        iterateAndBreakOn(t -> t.getIndex() == index - 1);
        return this;
    }
}
