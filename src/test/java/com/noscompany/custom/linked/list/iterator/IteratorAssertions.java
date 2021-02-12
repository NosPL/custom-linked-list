package com.noscompany.custom.linked.list.iterator;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorAssertions {

    /**
     * Checks position of iterator (0 means it is at the beginning)
     *
     * @param iterator
     * @param position expected position
     */
    public static void assertIsAtPosition(ListIterator iterator, int position) {
        assertEquals(position, iterator.nextIndex());
        assertEquals((position - 1), iterator.previousIndex());
    }

    public static void assertIsAtTheBeginning(ListIterator iterator) {
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());
        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::previous);
    }

    public static void assertIsAtTheEnd(ListIterator iterator) {
        assertFalse(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @SafeVarargs
    public static <T> void iterateFromHeadToTailAndCheckStateEveryStep(ListIterator<T> iterator, T... args) {
        int index = 0;
        while (iterator.hasNext()) {
            assertStateAndDoNext(
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
            assertStateAndDoPrevious(
                    iterator,
                    index,
                    (index + 1),
                    args[index]
            );
        }
        assertEquals(0, index);
    }

    public static <T> void assertStateAndDoNext(ListIterator<T> iterator, int previousIndex, int nextIndex, T nextElement) {
        assertEquals(iterator.previousIndex(), previousIndex);
        assertEquals(iterator.nextIndex(), nextIndex);
        assertEquals(iterator.next(), nextElement);
    }

    public static <T> void assertStateAndDoPrevious(ListIterator<T> iterator, int previousIndex, int nextIndex, T previousElement) {
        assertEquals(iterator.previousIndex(), previousIndex);
        assertEquals(iterator.nextIndex(), nextIndex);
        assertEquals(iterator.previous(), previousElement);
    }
}