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

    void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    Node<T> getPreviousNode() {
        return previousNode;
    }

    void setPreviousNode(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

    boolean hasPrevious() {
        return previousNode != null;
    }

    boolean hasNext() {
        return nextNode != null;
    }

    void removeNextNode() {
        if (this.hasNext()) {
            Node<T> nextNode = this.getNextNode();
            if (nextNode.hasNext())
                this.nextNode = nextNode.getNextNode();
        }
    }

    void removePreviousNode() {
        if (this.hasNext()) {
            Node<T> previousNode = this.getPreviousNode();
            if (previousNode.hasPrevious())
                this.previousNode = previousNode.getPreviousNode();
        }
    }

    boolean removeThis() {
        this.nextNode.setPreviousNode(this.getPreviousNode());
        return true;
    }

    Node<T> removeThisAndGetNext() {
        this.nextNode.setPreviousNode(this.getPreviousNode());
        return nextNode;
    }

    Node<T> removeThisAndGetPrevious() {
        this.nextNode.setPreviousNode(this.getPreviousNode());
        return previousNode;
    }

    boolean removeThisIfEquals(Object o) {
        if (element.equals(o)) {
            this.nextNode.setPreviousNode(this.getPreviousNode());
            return true;
        } else
            return false;
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
