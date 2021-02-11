package com.noscompany.custom.linked.list.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

import static com.noscompany.custom.linked.list.iterator.IteratorAssertions.*;

public class IteratingFromHeadToTailAndBack {

    @Test
    @DisplayName("iterating from head to tail and back")
    public void test1() {
        String[] args = new String[]{"a", "b", "c", "d"};
        ListIterator<String> iterator = new ArrayList<>(Arrays.asList(args)).listIterator();

        assertIsAtTheBeginning(iterator);

        iterateFromHeadToTailAndCheckStateEveryStep(iterator, args);

        assertIsAtTheEnd(iterator);

        iterateFromTailToHeadAndCheckStateEveryStep(iterator, args);

        assertIsAtTheBeginning(iterator);
    }
}