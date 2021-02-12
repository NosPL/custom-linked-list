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

    /**
     * Method for list iterator that goes through all its elements from head to tail
     * and back and check its state every step
     *
     * @param iterator tested iterator
     * @param args     arguments to be checked against iterator
     * @param <T>      type of list iterator and arguments
     */
    @SafeVarargs
    public static <T> void moveFromHeadToTailAndBackAndCheckStateEveryStep(ListIterator<T> iterator, T... args) {
        assertIsAtTheBeginning(iterator);
        iterateFromHeadToTailAndCheckStateEveryStep(iterator, args);
        assertIsAtTheEnd(iterator);
        iterateFromTailToHeadAndCheckStateEveryStep(iterator, args);
        assertIsAtTheBeginning(iterator);
    }

    public static <T> void moveAtTheEnd(ListIterator<T> iterator) {
        while (iterator.hasNext())
            iterator.next();
        assertIsAtTheEnd(iterator);
    }

    public static <T> void moveAtTheBeginning(ListIterator<T> iterator) {
        while (iterator.hasPrevious())
            iterator.previous();
        assertIsAtTheBeginning(iterator);
    }

    public static <T> void moveForwardToPosition(ListIterator<T> iterator, int position) {
        while (iterator.nextIndex() < position)
            iterator.next();
        assertPosition(iterator, position);
    }

    public static <T> void isEmpty(ListIterator<T> iterator) {
        assertPosition(iterator, 0);
        assertFalse(iterator.hasPrevious());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::previous);
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}