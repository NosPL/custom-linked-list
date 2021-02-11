package com.noscompany.custom.linked.list.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SettingElements {
    @Test
    @DisplayName("setting element at the beggining")
    public void test1() {
        ListIterator<String> iterator = new ArrayList<>(List.of("a", "b", "c")).listIterator();

        assertThrows(IllegalStateException.class, () -> iterator.set("x"));

        IteratorAssertions.assertIsAtTheBeginning(iterator);

        assertEquals(iterator.next(), "a");

        iterator.set("x");

        assertNextState(iterator, 0, 1, "b");
        assertNextState(iterator, 1, 2, "c");

        assertIsAtTheEnd(iterator);

        iterator.set("y");

        iterateFromTailToHeadAndCheckStateEveryStep(iterator, "x", "b", "y");

        assertIsAtTheBeginning(iterator);
    }
}
