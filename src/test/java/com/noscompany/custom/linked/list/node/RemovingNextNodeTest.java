package com.noscompany.custom.linked.list.node;

import com.noscompany.custom.linked.list.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemovingNextNodeTest {

    @Test
    @DisplayName("removing next node from head of 2-element chain makes it single node")
    public void test() {
        Node<String> tail = Node.single("y");
        Node<String> head = Node.head("x", tail);
        head.removeNext();
        assertTrue(head.isSingle());
        assertTrue(tail.isSingle());
    }

    @Test
    @DisplayName("removing next node from intermediate node of 3-element chain makes it tail of 2-element chain")
    public void test2() {
        Node<String> tail = Node.single("z");
        Node<String> head = Node.single("x");
        Node<String> intermediate = Node.intermediate("y", head, tail);
        intermediate.removeNext();
        assertTrue(intermediate.isTail());
        assertTrue(tail.isSingle());
    }
}
