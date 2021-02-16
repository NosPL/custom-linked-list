package com.noscompany.custom.linked.list.list;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypeToArrayTest {

    @Test
    public void test() {
        List<String> list = CustomLinkedList.of("a", "b", "c");
        Object[] array = list.toArray(new Object[0]);
        assertEquals(3, array.length);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
    }

    @Test
    public void test2() {
        List<String> list = CustomLinkedList.of("a", "b", "c");
        assertThrows(ArrayStoreException.class, () -> list.toArray(new Number[4]));
    }

    @Test
    public void test3() {
        List<String> list = CustomLinkedList.of();
        Number[] array = list.toArray(new Number[2]);
        assertEquals(2, array.length);
        assertArrayEquals(new Number[2], array);
    }

    @Test
    public void test4() {
        List<String> list = CustomLinkedList.of("a", "b", "c");
        Object[] array = list.toArray(new String[]{"1", "2", "3", "4", "5"});
        assertEquals(5, array.length);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertNull(array[3]);
        assertEquals("5", array[4]);
    }
}
