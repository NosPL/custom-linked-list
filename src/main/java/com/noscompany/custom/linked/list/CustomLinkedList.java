package com.noscompany.custom.linked.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomLinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CustomLinkedList(Node<T> head, Node<T> tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public static <T> CustomLinkedList<T> empty() {
        return new CustomLinkedList<>(null, null, 0);
    }

    @SafeVarargs
    public static <T> CustomLinkedList<T> of(T... elements) {
        Node<T> head = null;
        Node<T> tail = null;
        int i = 0;
        for (T t : elements) {
            if (i == 0) {
                head = Node.single(t);
                tail = head;
            } else {
                tail = tail.append(t);
                tail = tail.getNextNode();
            }
            i++;
        }
        return new CustomLinkedList<>(head, tail, i);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return CustomIterator.head(head)
                .iterateAndBreakOn(
                        t -> t.
                                getNode()
                                .getElement()
                                .equals(o))
                .isPresent();
    }

    @Override
    public Iterator<T> iterator() {
        return CustomIterator.head(head);
    }

    @Override
    public Object[] toArray() {
        if (this.isEmpty())
            return new Object[0];
        int thisSize = this.size();
        Object[] objects = new Object[thisSize];
        Node<T> next = head;
        for (int i = 0; i < thisSize; i++) {
            objects[0] = next.getElement();
            next = next.getNextNode();
        }
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (this.isEmpty()) {
            this.head = Node.single(t);
            this.tail = head;
        } else {
            tail = tail.append(t);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return CustomIterator.head(head)
                .iterateAndBreakOn(
                        t -> t
                                .getNode()
                                .getElement()
                                .equals(o))
                .map(IteratorEntry::getNode)
                .map(Node::removeThis)
                .orElse(false);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        return getNode(index).getElement();
    }

    private Node<T> getNode(int index) {
        validateRange(index);
        return CustomIterator
                .head(head)
                .iterateAndBreakOn(entry -> entry.getIndex() == index)
                .map(IteratorEntry::getNode)
                .orElse(null);
    }

    private void validateRange(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public T set(int index, T element) {
        validateRange(index);
        Node<T> node = getNode(index);
        T old = node.getElement();
        node.setElement(element);
        return old;
    }

    @Override
    public void add(int index, T element) {
        validateRange(index);
        getNode(index)
                .append(element);
        size++;
    }

    @Override
    public T remove(int index) {
        validateRange(index);
        Node<T> node = getNode(index);
        T element = node.getElement();
        node.removeThis();
        size--;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        return CustomIterator.head(head)
                .iterateAndBreakOn(
                        t -> t
                                .getNode()
                                .getElement()
                                .equals(o)
                )
                .map(IteratorEntry::getIndex)
                .orElse(-1);
    }

    @Override
    public int lastIndexOf(Object o) {
        if (this.isEmpty())
            return -1;
        Node<T> current = tail;
        int index = size - 1;
        do {
            if (current.getElement().equals(o))
                return index;
            index--;
        } while (current.hasPrevious());
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        return CustomIterator.head(head);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        validateRange(index);
        return CustomIterator.head(head)
                .customIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("CustomLinkedList = [");
        CustomIterator<T> iterator = CustomIterator.head(head);
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
