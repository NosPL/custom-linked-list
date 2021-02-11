package com.noscompany.custom.linked.list.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyIterator {

    @Test
    @DisplayName("should be empty")
    public void test() {
        ListIterator<String> iterator = new ArrayList<String>().listIterator();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertThrows(NoSuchElementException.class, iterator::previous);
        assertEquals(-1, iterator.previousIndex());
        assertEquals(0, iterator.nextIndex());
        assertThrows(IllegalStateException.class, () -> iterator.set("abc"));
        System.out.println(iterator.previous());
    }
}
