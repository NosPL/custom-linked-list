package com.noscompany.custom.linked.list.list;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectToArrayTest {

    @Test
    public void test() {
        List<Object> list = CustomLinkedList.of();
        Object[] array = list.toArray();
        assertEquals(0, array.length);
    }

    @Test
    public void test2() {
        Object[] array = {"a", "b", "c"};
        List<Object> list = CustomLinkedList.of(array);
        assertArrayEquals(array, list.toArray());
    }
}
