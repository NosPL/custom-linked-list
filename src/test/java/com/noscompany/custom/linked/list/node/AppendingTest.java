package com.noscompany.custom.linked.list.node;

import com.noscompany.custom.linked.list.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class AppendingTest {

    @Test
    @DisplayName("Appending should created bidirectional connection between 2 nodes")
    public void test() {
        Node<String> left = Node.single("x");
        left.append("y");
        Node<String> right = left.getNextNode();
        assertSame(left, right.getPreviousNode());
    }
}
