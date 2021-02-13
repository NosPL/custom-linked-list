package com.noscompany.custom.linked.list.node;

import com.noscompany.custom.linked.list.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class PrependingTest {

    @Test
    @DisplayName("Prepending should created bidirectional connection between 2 nodes")
    public void test() {
        Node<String> right = Node.single("y");
        right.prepend("x");
        Node<String> left = right.getPreviousNode();
        assertSame(right, left.getNextNode());
    }
}
