package com.noscompany.custom.linked.list.iterator;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.assertContainsOnly;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SettingElements {

    @Test
    @DisplayName("set() on empty iterator should crash")
    public void test() {
        ListIterator<Object> iterator = CustomLinkedList.empty().listIterator();
        assertThrows(IllegalStateException.class, () -> iterator.set(new Object()));
    }

    @Test
    @DisplayName("set() should set element returned by last next() call")
    public void test2() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        iterator.set("x");
        assertContainsOnly(iterator, "a", "x", "c");
    }

    @Test
    @DisplayName("set() should set element returned by last previous() call")
    public void test3() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("c", iterator.next());
        assertEquals("c", iterator.previous());
        iterator.set("x");
        assertContainsOnly(iterator, "a", "b", "x");
    }

    @Test
    @DisplayName("set() should crash if neither next(), set() nor previous() have been previously called")
    public void test4() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
        iterator.hasNext();
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
        iterator.hasPrevious();
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
        iterator.add("a");
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
        iterator.nextIndex();
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
        iterator.previousIndex();
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
    }

    @Test
    @DisplayName("set() should crash if remove() has been previously called")
    public void test5() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
    }

    @Test
    @DisplayName("set() should crash if add(..) has been previously called")
    public void test6() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        iterator.next();
        iterator.next();
        iterator.add("y");
        assertThrows(IllegalStateException.class, () -> iterator.set("x"));
    }
}
