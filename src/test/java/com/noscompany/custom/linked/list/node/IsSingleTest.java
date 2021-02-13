package com.noscompany.custom.linked.list.node;

import com.noscompany.custom.linked.list.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsSingleTest {

    @Test
    @DisplayName("Single node should be single")
    public void test() {
        Node<String> node = Node.single("x");
        assertTrue(node.isSingle());
    }

    @Test
    @DisplayName("head node should NOT be single")
    public void test2() {
        Node<String> head = Node.head("x", Node.single("y"));
        assertFalse(head.isSingle());
    }

    @Test
    @DisplayName("Tail node should NOT be single")
    public void test3() {
        Node<String> tail = Node.tail("x", Node.single("Y"));
        assertFalse(tail.isSingle());
    }

    @Test
    @DisplayName("Intermediate node should NOT be single")
    public void test4() {
        Node<String> intermediate = Node.intermediate("y", Node.single("x"), Node.single("z"));
        assertFalse(intermediate.isSingle());
    }
}
