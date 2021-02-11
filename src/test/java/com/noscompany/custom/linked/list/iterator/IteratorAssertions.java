package com.noscompany.custom.linked.list.iterator;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorAssertions {

    public static <T> void assertIsAtTheBeginning(ListIterator<T> iterator) {
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
            assertNextState(
                    iterator,
                    (index - 1),
                    index,
                    args[index]
            );
            index++;
        }
        assertEquals(args.length, index);
    }

    @SafeVarargs
    public static <T> void iterateFromTailToHeadAndCheckStateEveryStep(ListIterator<T> iterator, T... args) {
        int index = args.length;
        while (iterator.hasPrevious()) {
            index--;
            assertPreviousState(
                    iterator,
                    index,
                    (index + 1),
                    args[index]
            );
        }
        assertEquals(0, index);
    }

    public static <T> void assertNextState(ListIterator<T> iterator, int previousIndex, int nextIndex, T nextElement) {
        assertEquals(iterator.previousIndex(), previousIndex);
        assertEquals(iterator.nextIndex(), nextIndex);
        assertEquals(iterator.next(), nextElement);
    }

    public static <T> void assertPreviousState(ListIterator<T> iterator, int previousIndex, int nextIndex, T previousElement) {
        assertEquals(iterator.previousIndex(), previousIndex);
        assertEquals(iterator.nextIndex(), nextIndex);
        assertEquals(iterator.previous(), previousElement);
    }
}