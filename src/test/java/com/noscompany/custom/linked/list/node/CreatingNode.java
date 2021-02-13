package com.noscompany.custom.linked.list.node;

import com.noscompany.custom.linked.list.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatingNode {

    @Test
    @DisplayName("Creating single node")
    public void test() {
        Node<String> single = Node.single("x");
        assertFalse(single.hasPrevious());
        assertFalse(single.hasNext());
        assertEquals("x", single.getElement());
    }

    @Test
    @DisplayName("Creating head node")
    public void tes2() {
        Node<String> someNode = Node.single("y");
        Node<String> head = Node.head("x", someNode);
        assertFalse(head.hasPrevious());
        assertTrue(head.hasNext());
        assertNull(head.getPreviousNode());
        assertSame(head.getNextNode(), someNode);
        assertSame(someNode.getPreviousNode(), head);
        assertEquals("x", head.getElement());
        assertEquals("y", someNode.getElement());
    }

    @Test
    @DisplayName("Creating tail node")
    public void test3() {
        Node<String> someNode = Node.single("y");
        Node<String> tail = Node.tail("x", someNode);
        assertTrue(tail.hasPrevious());
        assertFalse(tail.hasNext());
        assertNull(tail.getNextNode());
        assertSame(tail.getPreviousNode(), someNode);
        assertSame(someNode.getNextNode(), tail);
        assertEquals("x", tail.getElement());
    }

    @Test
    @DisplayName("Creating intermediate node")
    public void test4() {
        Node<String> next = Node.single("z");
        Node<String> previous = Node.single("x");
        Node<String> intermediate = Node.intermediate("y", previous, next);
        assertTrue(intermediate.hasPrevious());
        assertTrue(intermediate.hasNext());
        assertSame(intermediate.getNextNode(), next);
        assertSame(intermediate.getPreviousNode(), previous);
        assertSame(previous.getNextNode(), intermediate);
        assertSame(next.getPreviousNode(), intermediate);
        assertEquals("y", intermediate.getElement());
    }
}
