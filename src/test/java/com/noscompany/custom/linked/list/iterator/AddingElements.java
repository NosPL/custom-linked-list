package com.noscompany.custom.linked.list.iterator;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddingElements {

    @Test
    @DisplayName("adding element at the beginning of the list iterator with multiple elements")
    public void test() {
//        given that there is iterator with elements "a", "b", "c"
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
//        and iterator is at the beginning
        assertIsAtTheBeginning(iterator);
//        when adding element "x"
        iterator.add("x");
//        then iterator is at position 1
        assertPosition(iterator, 1);
//        when moving one step back
//        then given element equals "x"
        assertEquals("x", iterator.previous());
//        and moving from head to tail and back works correctly
        moveFromHeadToTailAndBackAndCheckStateEveryStep(iterator, "x", "a", "b", "c");
    }

    @Test
    @DisplayName("adding element in the middle of the list iterator")
    public void test2() {
//        given that there is iterator with elements "a", "b", "c"
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
//        and iterator is at the beginning
        assertIsAtTheBeginning(iterator);
//        when moving at position 2 (between "b" and "c")
        moveForwardToPosition(iterator, 2);
//        and adding element "x"
        iterator.add("x");
//        then iterator is at position 3 (between "x" and "c")
        assertPosition(iterator, 3);
//        when moving at the beginning
        moveAtTheBeginning(iterator);
//        then moving from head to tail and back works correctly
        moveFromHeadToTailAndBackAndCheckStateEveryStep(iterator, "a", "b", "x", "c");
    }

    @Test
    @DisplayName("adding element at the end of the list iterator")
    public void test3() {
//        given that there is iterator with elements "a", "b", "c"
        ListIterator<String> iterator = CustomLinkedList.of("a", "b", "c").listIterator();
//        moved at the end
        moveAtTheEnd(iterator);
//        when adding element "x"
        iterator.add("x");
//        then iterator is still at the end
        assertIsAtTheEnd(iterator);
//        and then moving at the beginning works correctly
        moveAtTheBeginning(iterator);
//        and then moving from head to tail and back works correctly
        moveFromHeadToTailAndBackAndCheckStateEveryStep(iterator, "a", "b", "c", "x");
    }

    @Test
    @DisplayName("adding element to empty iterator")
    public void test4() {
//        given that there is empty iterator
        List<String> list = CustomLinkedList.empty();
        ListIterator<String> iterator = list.listIterator();
//        and iterator is at the beginning
        isEmpty(iterator);
//        when adding element
        iterator.add("a");
//        then iterator is still at the end
        assertIsAtTheEnd(iterator);
//        when moving 1 step back
//        then given element == "a"
        assertEquals("a", iterator.previous());
//        and iterator is at the beginning
        assertIsAtTheBeginning(iterator);
    }

    @Test
    @DisplayName("adding element at the beginning of the iterator with one element")
    public void test5() {
//        given that there is iterator with one element
        ListIterator<String> iterator = CustomLinkedList.of("b").listIterator();
//        and iterator is at the beginning
        assertIsAtTheBeginning(iterator);
//        when adding element
        iterator.add("a");
//        then iterator is at position 1
        assertPosition(iterator, 1);
//        when moving 1 step forward
//        then returned element == "b"
        assertEquals("b", iterator.next());
//        and is at the end
        assertIsAtTheEnd(iterator);
//        and moving from tail to head works correct
        iterateFromTailToHeadAndCheckStateEveryStep(iterator, "a", "b");
//        and iterator is at the beginning
        assertIsAtTheBeginning(iterator);
    }

    @Test
    @DisplayName("adding element at the end of the iterator with one element")
    public void test6() {
//        given that there is empty iterator
        ListIterator<String> iterator = CustomLinkedList.of("a").listIterator();
//        moved at the end
        moveAtTheEnd(iterator);
//        and adding element
        iterator.add("b");
//        then iterator is still at the end
        assertIsAtTheEnd(iterator);
//        and moving from tail to head works correct
        iterateFromTailToHeadAndCheckStateEveryStep(iterator, "a", "b");
//        and iterator is at the beginning
        assertIsAtTheBeginning(iterator);
    }
}
