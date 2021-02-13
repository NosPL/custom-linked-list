package com.noscompany.custom.linked.list.cursor;

import com.noscompany.custom.linked.list.Node;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Empty<T> extends Cursor<T> {

    public Empty() {
        super(null);
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
    public Optional<Cursor<T>> moveForward() {
        return Optional.empty();
    }

    @Override
    public Optional<Cursor<T>> moveBackward() {
        return Optional.empty();
    }

    @Override
    public Node<T> getNode() {
        Optional.empty().orElseThrow(NoSuchElementException::new);
        return null;
    }

    @Override
    public void set(T t) {
        throw new IllegalStateException();
    }

    @Override
    public Cursor<T> add(T t) {
        return new End<>(Node.single(t));
    }


}
