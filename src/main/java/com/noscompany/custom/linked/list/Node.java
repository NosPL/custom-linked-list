package com.noscompany.custom.linked.list;

class Node<T> {
    private T element;
    private Node<T> previousNode;
    private Node<T> nextNode;

    private Node(T element, Node<T> previousNode, Node<T> nextNode) {
        this.element = element;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }

    static <T> Node<T> single(T t) {
        return new Node<>(t, null, null);
    }

    static <T> Node<T> withPrevious(T t, Node<T> previous) {
        return new Node<>(t, previous, null);
    }

    static <T> Node<T> withNext(T t, Node<T> next) {
        return new Node<>(t, null, next);
    }

    static <T> Node<T> withPreviousAndNext(T t, Node<T> previous, Node<T> next) {
        return new Node<>(t, previous, next);
    }

    T getElement() {
        return element;
    }

    void setElement(T element) {
        this.element = element;
    }

    Node<T> getNextNode() {
        return nextNode;
    }

    Node<T> getPreviousNode() {
        return previousNode;
    }

    boolean hasPrevious() {
        return previousNode != null;
    }

    boolean hasNext() {
        return nextNode != null;
    }

    boolean removeThis() {
        this.nextNode.previousNode = this.getPreviousNode();
        return true;
    }

    void append(T t) {
        if (nextNode == null) {
            nextNode = Node.withPrevious(t, this);
        } else {
            nextNode = Node.withPreviousAndNext(t, this, nextNode);
        }
    }

    void prepend(T t) {
        if (previousNode == null) {
            previousNode = Node.withNext(t, this);
        } else {
            previousNode = Node.withPreviousAndNext(t, previousNode, this);
        }
    }
}
