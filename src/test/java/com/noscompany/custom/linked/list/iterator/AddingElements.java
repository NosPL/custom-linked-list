package com.noscompany.custom.linked.list.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddingElements {

    @Test
    @DisplayName("adding on the beginning should succeed")
    public void test() {
        List<String> objects = new ArrayList<>(List.of("a", "b", "c"));
        ListIterator<String> iterator = objects.listIterator();
        assertIsAtTheBeginning(iterator);
        iterator.add("x");
        assertEquals(0, iterator.previousIndex());
        assertEquals(1, iterator.nextIndex());
        assertEquals("x", iterator.previous());
        assertIsAtTheBeginning(iterator);
        iterateFromHeadToTailAndCheckStateEveryStep(iterator, "x", "a", "b", "c");
        assertIsAtTheEnd(iterator);
        iterateFromTailToHeadAndCheckStateEveryStep(iterator, "x", "a", "b", "c");
        assertIsAtTheBeginning(iterator);
    }
}
