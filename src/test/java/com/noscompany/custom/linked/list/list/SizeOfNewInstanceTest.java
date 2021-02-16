package com.noscompany.custom.linked.list.list;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SizeOfNewInstanceTest {

    @Test
    @DisplayName("empty() on empty list should return true and size() should return 0")
    public void test() {
        List<Object> list = CustomLinkedList.of();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("empty() on list with 3 elements should return false and size() should return 3")
    public void test2() {
        List<String> list = CustomLinkedList.of("a", "b", "c");
        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
    }
}