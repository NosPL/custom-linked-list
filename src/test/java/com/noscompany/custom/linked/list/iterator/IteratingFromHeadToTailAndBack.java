package com.noscompany.custom.linked.list.iterator;

import com.noscompany.custom.linked.list.CustomLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;

public class IteratingFromHeadToTailAndBack {

    @Test
    @DisplayName("iterating from head to tail and back through iterator with multiple elements")
    public void test1() {
//        when getting iterator from list with elements "a", "b", "c", "d"
        ListIterator<String> iterator = CustomLinkedList
                .of("a", "b", "c", "d")
                .listIterator();
//        then firstly iterator is at the beginning
        assertIsAtTheBeginning(iterator);
//        and then it correctly goes from head to tail
        iterateFromHeadToTailAndCheckStateEveryStep(iterator, "a", "b", "c", "d");
//        and then it is at the end
        assertIsAtTheEnd(iterator);
//        and then it correctly goes back
        iterateFromTailToHeadAndCheckStateEveryStep(iterator, "a", "b", "c", "d");
//        and then it is at the beginning again
        assertIsAtTheBeginning(iterator);
    }
}