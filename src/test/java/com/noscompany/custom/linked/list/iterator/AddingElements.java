package com.noscompany.custom.linked.list.iterator;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddingElements {

    @Test
    @DisplayName("adding element at the beginning of the list iterator")
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
        String x = iterator.previous();
//        then given element equals "x"
        assertEquals("x", x);
//        and moving from head to tail and back works correctly
        moveFromHeadToTailAndBackAndCheckStateEveryStep(iterator, "x", "a", "b", "c");
    }
}
