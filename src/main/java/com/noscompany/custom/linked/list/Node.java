package com.noscompany.custom.linked.list;

public class Node<T> {
    private final T element;
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

    public static <T> Node<T> withPrevious(T t, Node<T> previous) {
        return new Node<>(t, previous, null);
    }

    public static <T> Node<T> withNext(T t, Node<T> next) {
        return new Node<>(t, null, next);
    }

    public static <T> Node<T> withPreviousAndNext(T t, Node<T> previous, Node<T> next) {
        return new Node<>(t, previous, next);
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
            nextNode = Node.withPrevious(t, this);
        } else {
            Node<T> newNextNode = Node.withPreviousAndNext(t, this, this.nextNode);
            this.nextNode.previousNode = newNextNode;
            this.nextNode = newNextNode;
        }
    }

    public void prepend(T t) {
        if (previousNode == null) {
            previousNode = Node.withNext(t, this);
        } else {
            Node<T> newPreviousNode = Node.withPreviousAndNext(t, this.previousNode, this);
            this.previousNode.nextNode = newPreviousNode;
            this.previousNode = newPreviousNode;
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
}