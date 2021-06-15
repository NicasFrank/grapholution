package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfun;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBoolean;
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
        GenotypeBoolean genotype = new GenotypeBoolean(8);
        Fitnessfun<Boolean> fitnessfun = new OneMaxEvaluator();
        Mutator<Boolean> mutator = new SwitchOneBit();
        hillclimber = new Hillclimber<>(genotype, fitnessfun, mutator);
        result = new ArrayList<>();
        for(int i = 0; i<8; i++){
            result.add(Boolean.TRUE);
        }
    }

    @Test
    void testHillClimb() {
        Genotype<Boolean> expected = hillclimber.run();
        for(int i = 0; i<8; i++){
            assertEquals(result.get(i), expected.valueAt(i));
        }
    }
}