package com.noscompany.custom.linked.list.iterator;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorAssertions {

    /**
     * Checks position of iterator (0 means it is at the beginning)
     *
     * @param iterator tested iterator
     * @param position expected position
     */
    public static <T> void assertPosition(ListIterator<T> iterator, int position) {
        assertEquals(position, iterator.nextIndex());
        assertEquals((position - 1), iterator.previousIndex());
    }

    public static <T> void assertIsAtTheBeginning(ListIterator<T> iterator) {
        assertPosition(iterator, 0);
        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::previous);
    }

    public static <T> void assertIsAtTheEnd(ListIterator<T> iterator) {
        assertFalse(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @SafeVarargs
    public static <T> void iterateFromHeadToTailAndCheckStateEveryStep(ListIterator<T> iterator, T... args) {
        int index = 0;
        while (iterator.hasNext()) {
            assertPosition(iterator, index);
            assertEquals(args[index], iterator.next());
            index++;
        }
        assertEquals(args.length, index);
    }

    @SafeVarargs
    public static <T> void iterateFromTailToHeadAndCheckStateEveryStep(ListIterator<T> iterator, T... args) {
        int index = args.length;
        while (iterator.hasPrevious()) {
            assertPosition(iterator, index);
            index--;
            assertEquals(args[index], iterator.previous());
        }
        assertEquals(0, index);
    }
}