package com.noscompany.custom.linked.list.iterator;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForEachRemaining {

    @Test
    @DisplayName("'forEachRemaining(..)' should not be called once for empty iterator")
    public void test() {
        ListIterator<Object> iterator = CustomLinkedList.empty().listIterator();
        assertIsEmpty(iterator);
        SomeClass<Object> someClass = new SomeClass<>();
        iterator.forEachRemaining(someClass::consume);
        assertEquals(0, someClass.getInvocationCount());
    }

    @Test
    @DisplayName("'forEachRemaining(..)' should execute for every element in non empty iterator")
    public void test2() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        assertIsAtTheBeginning(iterator);
        SomeClass<String> someClass = new SomeClass<>();
        iterator.forEachRemaining(someClass::consume);
        assertEquals(3, someClass.getInvocationCount());
    }

    @Test
    @DisplayName("'forEachRemaining(..)' should not be called once if cursor is ath the end of iterator")
    public void test3() {
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
        moveAtTheEnd(iterator);
        SomeClass<String> someClass = new SomeClass<>();
        iterator.forEachRemaining(someClass::consume);
        assertEquals(0, someClass.getInvocationCount());
    }
}
