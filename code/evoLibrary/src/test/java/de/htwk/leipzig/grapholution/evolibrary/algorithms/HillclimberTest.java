package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfun;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBool;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HillclimberTest {

    static Hillclimber<Boolean> hillclimber;
    static ArrayList<Boolean> result;

    @BeforeAll
    static void initHillclimber() {
        ArrayList<Boolean> values = new ArrayList<>();
        for(int i = 0; i<8; i++){
            values.add(Boolean.FALSE);
        }
        GenotypeBool genotype = new GenotypeBool(values);
        Fitnessfun fitnessfun = new OneMaxEvaluator();
        Mutator mutator = new SwitchOneBit();
        hillclimber = new Hillclimber<Boolean>(genotype, fitnessfun, mutator);
        result = new ArrayList<>();
        for(int i = 0; i<8; i++){
            result.add(Boolean.TRUE);
        }
    }

    @Test
    void testHillClimb() {
        GenotypeBool expected = new GenotypeBool(result);
        for(int i = 0; i<8; i++){
            assertEquals(result.get(i), expected.valueAt(i));
        }
    }
}