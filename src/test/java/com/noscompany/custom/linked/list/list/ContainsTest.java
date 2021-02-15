package com.noscompany.custom.linked.list.list;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsTest {

    @Test
    public void test() {
        assertTrue(CustomLinkedList.of("x").contains("x"));
    }

    @Test
    public void tes2() {
        assertFalse(CustomLinkedList.of().contains("x"));
    }

    @Test
    public void test3() {
        List<String> list = CustomLinkedList.of("a", "b", "c");
        assertTrue(list.contains("a"));
        assertTrue(list.contains("b"));
        assertTrue(list.contains("c"));
    }

    @Test
    public void tes4() {
        List<String> list = CustomLinkedList.of();
        list.add("x");
        assertTrue(list.contains("x"));
    }
}
