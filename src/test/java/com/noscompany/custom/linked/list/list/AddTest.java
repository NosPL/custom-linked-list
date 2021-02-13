package com.noscompany.custom.linked.list.list;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddTest {

    @Test
    @DisplayName("adding element should increase list.size() by 1")
    public void test() {
        CustomLinkedList<String> list = CustomLinkedList.empty();
        assertTrue(list.isEmpty());
        list.add("x");
        assertEquals(1, list.size());
        list.add("x");
        assertEquals(2, list.size());
        list.add("x");
        assertEquals(3, list.size());
    }
}
