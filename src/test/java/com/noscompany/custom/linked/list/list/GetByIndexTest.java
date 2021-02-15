package com.noscompany.custom.linked.list.list;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetByIndexTest {

    @Test
    @DisplayName("trying to get() element from empty list should crash")
    public void test() {
        CustomLinkedList<Object> list = CustomLinkedList.of();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    @DisplayName("trying to get() element from non-empty list with (index >= size() && index < 0) should crash")
    public void test1() {
        CustomLinkedList<String> list = CustomLinkedList.of("a", "b", "c");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
    }

    @Test
    @DisplayName("for list{'a', 'b', 'c'} get(0) should == 'a', get(1) should == 'b', get(2) should == 'c'")
    public void test3() {
        CustomLinkedList<String> list = CustomLinkedList.of("a", "b", "c");
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }
}