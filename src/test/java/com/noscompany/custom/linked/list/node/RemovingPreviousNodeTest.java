package com.noscompany.custom.linked.list.node;

import com.noscompany.custom.linked.list.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemovingPreviousNodeTest {

    @Test
    @DisplayName("removing previous node from tail of 2-element chain makes it single node")
    public void test() {
        Node<String> tail = Node.single("y");
        Node<String> head = Node.head("x", tail);
        tail.removePrevious();
        assertTrue(head.isSingle());
        assertTrue(tail.isSingle());
    }

    @Test
    @DisplayName("removing previous node from intermediate node of 3-element chain makes it head of 2-element chain")
    public void test2() {
        Node<String> tail = Node.single("z");
        Node<String> head = Node.single("x");
        Node<String> intermediate = Node.intermediate("y", head, tail);
        intermediate.removePrevious();
        assertTrue(intermediate.isHead());
        assertTrue(head.isSingle());
    }
}
