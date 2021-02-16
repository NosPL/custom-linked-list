package com.noscompany.custom.linked.list.cursor;

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

    static <T> Node<T> tail(T t, Node<T> previous) {
        Node<T> node = new Node<>(t, previous, null);
        previous.nextNode = node;
        return node;
    }

    static <T> Node<T> head(T t, Node<T> next) {
        Node<T> node = new Node<>(t, null, next);
        next.previousNode = node;
        return node;
    }

    static <T> Node<T> intermediate(T t, Node<T> previous, Node<T> next) {
        Node<T> node = new Node<>(t, previous, next);
        next.previousNode = node;
        previous.nextNode = node;
        return node;
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

    void append(T t) {
        if (nextNode == null) {
            Node.tail(t, this);
        } else {
            Node.intermediate(t, this, this.nextNode);
        }
    }

    void prepend(T t) {
        if (previousNode == null) {
            Node.head(t, this);
        } else {
            Node.intermediate(t, this.previousNode, this);
        }
    }

    void removeNext() {
        if (nextNode == null)
            return;
        else if (nextNode.hasNext()) {
            Node<T> oldNextNode = nextNode;
            connectAsNext(nextNode.getNextNode());
            oldNextNode.detach();
        } else {
            nextNode.previousNode = null;
            nextNode = null;
        }
    }

    void removePrevious() {
        if (previousNode == null)
            return;
        else if (previousNode.hasPrevious()) {
            Node<T> oldPreviousNode = previousNode;
            connectAsPrevious(previousNode.getPreviousNode());
            oldPreviousNode.detach();
        } else {
            previousNode.nextNode = null;
            previousNode = null;
        }
    }

    private void connectAsNext(Node<T> node) {
        nextNode = node;
        node.previousNode = this;
    }

    private void connectAsPrevious(Node<T> node) {
        previousNode = node;
        node.nextNode = this;
    }

    private void detach() {
        previousNode = null;
        nextNode = null;
    }

    boolean isSingle() {
        return previousNode == null && nextNode == null;
    }

    boolean isTail() {
        return previousNode != null && nextNode == null;
    }

    boolean isHead() {
        return previousNode == null && nextNode != null;
    }
}