package com.noscompany.custom.linked.list;

import java.util.*;

public class CustomLinkedList<T> implements List<T> {
    private CustomIterator<T> customIterator;

    private CustomLinkedList(CustomIterator<T> customIterator) {
        this.customIterator = customIterator;
    }

    @SafeVarargs
    public static <T> CustomLinkedList<T> of(T... elements) {
        CustomIterator<T> iterator = CustomIterator.empty();
        for (T t : elements) {
            Objects.requireNonNull(t);
            iterator.add(t);
        }
        return new CustomLinkedList<>(iterator);
    }

    @Override
    public int size() {
        return customIterator.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Objects.requireNonNull(o);
        for (T t : this) {
            if (t.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        if (this.isEmpty())
            return new Object[0];
        ListIterator<T> iterator = listIterator();
        Object[] objects = new Object[this.size()];
        while (iterator.hasNext())
            objects[iterator.nextIndex()] = iterator.next();
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        Objects.requireNonNull(a);
        a = (size() > a.length) ? (T1[]) new Object[size()] : a;
        int i = 0;
        for (T t : this)
            try {
                a[i++] = (T1) t;
            } catch (ClassCastException c) {
                throw new ArrayStoreException();
            }
        if (a.length > size())
            a[size()] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        Objects.requireNonNull(t);
        customIterator.moveAtTheEnd();
        customIterator.add(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(o)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkForNulls(c);
        int containCount = 0;
        for (Object o : c) {
            if (this.contains(o))
                containCount++;
        }
        return containCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        checkForNulls(c);
        customIterator.moveAtTheEnd();
        for (T t : c) {
            customIterator.add(t);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throwOutOfBoundIf((index < 0 || index > size()));
        checkForNulls(c);
        boolean changed = false;
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                for (T t : c)
                    iterator.add(t);
                changed = true;
            }
            iterator.next();
        }
        return changed;
    }

    private void throwOutOfBoundIf(boolean value) {
        if (value) throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkForNulls(c);
        boolean changed = false;
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            inner:
            for (Object o : c) {
                if (next.equals(o)) {
                    iterator.remove();
                    changed = true;
                    break inner;
                }
            }
        }
        return changed;
    }

    private void checkForNulls(Collection<?> c) {
        Objects.requireNonNull(c);
        for (Object o : c)
            Objects.requireNonNull(o);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkForNulls(c);
        boolean changed = false;
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (!c.contains(next)) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        customIterator = CustomIterator.empty();
    }

    @Override
    public T get(int index) {
        throwOutOfBoundIf(index < 0 || index >= size());
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index)
                return iterator.next();
            else
                iterator.next();
        }
        throw new RuntimeException("did not found element for index: " + index);
    }

    @Override
    public T set(int index, T element) {
        throwOutOfBoundIf(index < 0 || index >= size());
        Objects.requireNonNull(element);
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (iterator.previousIndex() == index) {
                iterator.set(element);
                return next;
            }
        }
        throw new RuntimeException("did not find element with index: " + index);
    }

    @Override
    public void add(int index, T element) {
        Objects.requireNonNull(element);
        throwOutOfBoundIf(index < 0 || index > size());
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.nextIndex() == index) {
                iterator.add(element);
            }
        }
    }

    @Override
    public T remove(int index) {
        throwOutOfBoundIf(index < 0 || index >= size());
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (iterator.previousIndex() == index) {
                iterator.remove();
                return next;
            }
        }
        throw new RuntimeException("not implemented");
    }

    @Override
    public int indexOf(Object o) {
        Objects.requireNonNull(o);
        ListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(o))
                return iterator.previousIndex();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Objects.requireNonNull(o);
        customIterator.moveAtTheEnd();
        while (customIterator.hasPrevious())
            if (customIterator.previous().equals(o))
                return customIterator.nextIndex();
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        customIterator.moveAtTheBeginning();
        customIterator.resetLastOperation();
        return customIterator;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throwOutOfBoundIf(index < 0 || index > size());
        customIterator.moveAtTheBeginning();
        while (customIterator.hasNext() && customIterator.nextIndex() != index)
            customIterator.next();
        customIterator.resetLastOperation();
        return customIterator;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throwOutOfBoundIf(fromIndex < 0 || toIndex > size() || fromIndex > toIndex);
        ListIterator<T> iterator = listIterator();
        CustomLinkedList<T> result = CustomLinkedList.of();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            if (index >= fromIndex && index < toIndex)
                result.add(iterator.next());
        }
        return result;
    }
}