package com.noscompany.custom.linked.list;

public class Node<T> {
    private T element;
    private Node<T> previousNode;
    private Node<T> nextNode;

    private Node(T element, Node<T> previousNode, Node<T> nextNode) {
        this.element = element;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }

    public static <T> Node<T> single(T t) {
        return new Node<>(t, null, null);
    }

    public static <T> Node<T> tail(T t, Node<T> previous) {
        Node<T> node = new Node<>(t, previous, null);
        previous.nextNode = node;
        return node;
    }

    public static <T> Node<T> head(T t, Node<T> next) {
        Node<T> node = new Node<>(t, null, next);
        next.previousNode = node;
        return node;
    }

    public static <T> Node<T> intermediate(T t, Node<T> previous, Node<T> next) {
        Node<T> node = new Node<>(t, previous, next);
        next.previousNode = node;
        previous.nextNode = node;
        return node;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public Node<T> getPreviousNode() {
        return previousNode;
    }

    public boolean hasPrevious() {
        return previousNode != null;
    }

    public boolean hasNext() {
        return nextNode != null;
    }

    public void append(T t) {
        if (nextNode == null) {
            Node.tail(t, this);
        } else {
            Node.intermediate(t, this, this.nextNode);
        }
    }

    public void prepend(T t) {
        if (previousNode == null) {
            Node.head(t, this);
        } else {
            Node.intermediate(t, this.previousNode, this);
        }
    }

    public void removeNext() {
        if (nextNode == null)
            return;
        else if (nextNode.hasNext()) {
            Node<T> oldNextNode = nextNode;
            nextNode = nextNode.getNextNode();
            nextNode.previousNode = this;
            oldNextNode.nullifyNeighbours();
        } else {
            nextNode.previousNode = null;
            nextNode = null;
        }
    }

    public void removePrevious() {
        if (previousNode == null)
            return;
        else if (previousNode.hasPrevious()) {
            Node<T> oldPreviousNode = previousNode;
            previousNode = previousNode.getPreviousNode();
            previousNode.nextNode = this;
            oldPreviousNode.nullifyNeighbours();
        } else {
            previousNode.nextNode = null;
            previousNode = null;
        }
    }

    private void nullifyNeighbours() {
        previousNode = null;
        nextNode = null;
    }

    public boolean isSingle() {
        return previousNode == null && nextNode == null;
    }

    public boolean isTail() {
        return previousNode != null && nextNode == null;
    }

    public boolean isHead() {
        return previousNode == null && nextNode != null;
    }
}