package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringHillclimberTest {

    static StringHillClimber hillclimber;

    @BeforeAll
    static void initHillclimber() {
        hillclimber = new StringHillClimber();
    }

    @Test
    void testEvalute() {
        assertEquals(0, hillclimber.evaluate("00000000"));
        assertEquals(1, hillclimber.evaluate("00001000"));
        assertEquals(2, hillclimber.evaluate("00001001"));
        assertEquals(3, hillclimber.evaluate("0100101"));
        assertEquals(4, hillclimber.evaluate("000011100000001"));
    }

    @Test
    void testMutate() {
        assertNotEquals("00000000", hillclimber.mutate("00000000"));
        assertNotEquals("11110000", hillclimber.mutate("11110000"));
        assertNotEquals("1111", hillclimber.mutate("1111"));

        int diff = hillclimber.evaluate("00000000") - hillclimber.evaluate(hillclimber.mutate("00000000"));
        assertEquals(1, Math.abs(diff));
        diff = hillclimber.evaluate("11110000") - hillclimber.evaluate(hillclimber.mutate("11110000"));
        assertEquals(1, Math.abs(diff));
        diff = hillclimber.evaluate("1111") - hillclimber.evaluate(hillclimber.mutate("1111"));
        assertEquals(1, Math.abs(diff));
    }

    @Test
    void testHillClimb() {
        assertEquals("11111111", hillclimber.run("00000000"));
    }
}