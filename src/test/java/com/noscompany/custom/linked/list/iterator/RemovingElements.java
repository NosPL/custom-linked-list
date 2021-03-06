package com.noscompany.custom.linked.list.iterator;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class RemovingElements {

    @Test
    @DisplayName("remove() on empty iterator should crash")
    public void test() {
        ListIterator<Object> iterator = CustomLinkedList.of().listIterator();
        assertIsEmpty(iterator);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("remove() should delete element returned by last next() call")
    public void test2() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        assertEquals("a", iterator.next());
        assertPosition(iterator, 1);
        iterator.remove();
        assertPosition(iterator, 0);
        assertContainsOnly(iterator, "b", "c");
    }

    @Test
    @DisplayName("remove() should delete element returned by last previous() call")
    public void test3() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        iterator.next();
        iterator.next();
        assertEquals("b", iterator.previous());
        assertPosition(iterator, 1);
        iterator.remove();
        assertPosition(iterator, 1);
        assertContainsOnly(iterator, "a", "c");
    }

    @Test
    @DisplayName("remove() should crash if neither next(), set() nor previous() have been previously called")
    public void test4() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        assertThrows(IllegalStateException.class, iterator::remove);
        iterator.hasNext();
        assertThrows(IllegalStateException.class, iterator::remove);
        iterator.hasPrevious();
        assertThrows(IllegalStateException.class, iterator::remove);
        iterator.add("a");
        assertThrows(IllegalStateException.class, iterator::remove);
        iterator.nextIndex();
        assertThrows(IllegalStateException.class, iterator::remove);
        iterator.previousIndex();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("remove() should delete element set by last set(..) call")
    public void test5() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        iterator.next();
        iterator.next();
        iterator.set("x");
        assertDoesNotThrow(iterator::remove);
        assertContainsOnly(iterator, "a", "c");
    }
}
