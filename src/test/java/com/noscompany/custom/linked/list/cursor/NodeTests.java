package com.noscompany.custom.linked.list.cursor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTests {
    @Test
    @DisplayName("Appending should created bidirectional connection between 2 nodes")
    public void test() {
        Node<String> left = Node.single("x");
        left.append("y");
        Node<String> right = left.getNextNode();
        assertSame(left, right.getPreviousNode());
    }

    @Test
    @DisplayName("Creating single node")
    public void tes2() {
        Node<String> single = Node.single("x");
        assertFalse(single.hasPrevious());
        assertFalse(single.hasNext());
        assertEquals("x", single.getElement());
    }

    @Test
    @DisplayName("Creating head node")
    public void tes3() {
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
    public void test4() {
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
    public void test5() {
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

    @Test
    @DisplayName("Single node should be single")
    public void tes6() {
        Node<String> node = Node.single("x");
        assertTrue(node.isSingle());
    }

    @Test
    @DisplayName("head node should NOT be single")
    public void test7() {
        Node<String> head = Node.head("x", Node.single("y"));
        assertFalse(head.isSingle());
    }

    @Test
    @DisplayName("Tail node should NOT be single")
    public void test8() {
        Node<String> tail = Node.tail("x", Node.single("Y"));
        assertFalse(tail.isSingle());
    }

    @Test
    @DisplayName("Intermediate node should NOT be single")
    public void test9() {
        Node<String> intermediate = Node.intermediate("y", Node.single("x"), Node.single("z"));
        assertFalse(intermediate.isSingle());
    }

    @Test
    @DisplayName("Prepending should created bidirectional connection between 2 nodes")
    public void test10() {
        Node<String> right = Node.single("y");
        right.prepend("x");
        Node<String> left = right.getPreviousNode();
        assertSame(right, left.getNextNode());
    }

    @Test
    @DisplayName("removing next node from head of 2-element chain makes it single node")
    public void test11() {
        Node<String> tail = Node.single("y");
        Node<String> head = Node.head("x", tail);
        head.removeNext();
        assertTrue(head.isSingle());
        assertTrue(tail.isSingle());
    }

    @Test
    @DisplayName("removing next node from intermediate node of 3-element chain makes it tail of 2-element chain")
    public void test12() {
        Node<String> tail = Node.single("z");
        Node<String> head = Node.single("x");
        Node<String> intermediate = Node.intermediate("y", head, tail);
        intermediate.removeNext();
        assertTrue(intermediate.isTail());
        assertTrue(tail.isSingle());
    }

    @Test
    @DisplayName("removing previous node from tail of 2-element chain makes it single node")
    public void test13() {
        Node<String> tail = Node.single("y");
        Node<String> head = Node.head("x", tail);
        tail.removePrevious();
        assertTrue(head.isSingle());
        assertTrue(tail.isSingle());
    }

    @Test
    @DisplayName("removing previous node from intermediate node of 3-element chain makes it head of 2-element chain")
    public void test14() {
        Node<String> tail = Node.single("z");
        Node<String> head = Node.single("x");
        Node<String> intermediate = Node.intermediate("y", head, tail);
        intermediate.removePrevious();
        assertTrue(intermediate.isHead());
        assertTrue(head.isSingle());
    }
}
