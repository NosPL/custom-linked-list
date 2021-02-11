package com.noscompany.custom.linked.list.iterator;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyIterator {

    @Test
    @DisplayName("check state of iterator from empty list")
    public void test() {
        List<String> list = CustomLinkedList.empty();
        assertTrue(list.isEmpty());
        ListIterator<String> iterator = list.listIterator();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertThrows(NoSuchElementException.class, iterator::previous);
        assertEquals(-1, iterator.previousIndex());
        assertEquals(0, iterator.nextIndex());
        assertThrows(IllegalStateException.class, () -> iterator.set("abc"));
    }
}
