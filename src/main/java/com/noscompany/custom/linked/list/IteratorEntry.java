package com.noscompany.custom.linked.list;

class IteratorEntry<T> {
    private final int index;
    private final Node<T> node;

    public IteratorEntry(int index, Node<T> node) {
        this.index = index;
        this.node = node;
    }

    public int getIndex() {
        return index;
    }

    public Node<T> getNode() {
        return node;
    }
}
