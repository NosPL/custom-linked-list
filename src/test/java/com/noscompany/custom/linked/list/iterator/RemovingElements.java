package com.noscompany.custom.linked.list.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.assertIsEmpty;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovingElements {

    @Test
    @DisplayName("remove() on empty iterator should crash")
    public void test() {
        ListIterator<Object> iterator = new ArrayList<>().listIterator();
        assertIsEmpty(iterator);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("remove() should delete element returned by last next() call")
    public void test2() {
        ListIterator<String> iterator = new ArrayList<>(of("a", "b", "c")).listIterator();

    }

    @Test
    @DisplayName("remove() should delete element returned by last previous() call")
    public void test3() {
        ListIterator<String> iterator = new ArrayList<>(of("a", "b", "c")).listIterator();
    }

    @Test
    @DisplayName("remove() should crash if neither next() nor previous() have been previously called")
    public void test4() {
        ListIterator<String> iterator = new ArrayList<>(of("a", "b", "c")).listIterator();
    }

    @Test
    @DisplayName("remove() should crash if add() has been previously called")
    public void test5() {
        ListIterator<String> iterator = new ArrayList<>(of("a", "b", "c")).listIterator();
    }

    @Test
    @DisplayName("remove() should crash if remove() has been previously called")
    public void test6() {
        ListIterator<String> iterator = new ArrayList<>(of("a", "b", "c")).listIterator();
    }
}
