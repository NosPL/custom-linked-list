package com.noscompany.custom.linked.list;

class Node<T> {
    private final T element;
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

    void append(T t) {
        if (nextNode == null) {
            nextNode = Node.withPrevious(t, this);
        } else {
            Node<T> newNextNode = Node.withPreviousAndNext(t, this, this.nextNode);
            this.nextNode.previousNode = newNextNode;
            this.nextNode = newNextNode;
        }
    }

    void prepend(T t) {
        if (previousNode == null) {
            previousNode = Node.withNext(t, this);
        } else {
            Node<T> newPreviousNode = Node.withPreviousAndNext(t, this.previousNode, this);
            this.previousNode.nextNode = newPreviousNode;
            this.previousNode = newPreviousNode;
        }
    }
}
