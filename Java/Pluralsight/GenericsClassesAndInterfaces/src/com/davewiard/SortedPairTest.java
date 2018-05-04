package com.davewiard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortedPairTest {
    @Test
    public void shouldRetainOrderOfOrderedPair() {
        SortedPair<Integer> pair = new SortedPair<>(1, 2);

        assertEquals(1, pair.getFirst().intValue());
        assertEquals(2, pair.getSecond().intValue());
    }

    @Test
    public void shouldFlipOrderOfMisorderedPair() {
        SortedPair<Integer> pair = new SortedPair<>(2, 1);

        assertEquals(1, pair.getFirst().intValue());
        assertEquals(2, pair.getSecond().intValue());
    }
}
