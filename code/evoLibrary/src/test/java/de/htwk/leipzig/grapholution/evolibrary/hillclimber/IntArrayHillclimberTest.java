package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntArrayHillclimberTest {

    static IntArrayHillclimber intArrayHillclimber;

    @BeforeAll
    static void initHillclimber() {
        intArrayHillclimber = new IntArrayHillclimber();
    }

    @Test
    void testEvalute() {
        assertEquals(1, intArrayHillclimber.evaluate(new int[]{0, 1}));
        assertEquals(0, intArrayHillclimber.evaluate(new int[]{1, 0}));
        assertEquals(1, intArrayHillclimber.evaluate(new int[]{2, 3, 1}));
        assertEquals(0, intArrayHillclimber.evaluate(new int[]{3, 2, 1}));
    }

    @Test
    void testMutate() {
        assertNotEquals(new int[]{3, 2, 1}, intArrayHillclimber.mutate(new int[]{3, 2, 1}));
        assertNotEquals(new int[]{2, 1}, intArrayHillclimber.mutate(new int[]{2, 1}));
        assertNotEquals(new int[]{1, 2, 3}, intArrayHillclimber.mutate(new int[]{1, 2, 3}));
    }

    @Test
    void testHillClimb() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, intArrayHillclimber.run(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }
}
