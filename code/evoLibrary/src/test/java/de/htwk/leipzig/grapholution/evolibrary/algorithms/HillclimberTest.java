package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfun;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBoolean;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HillclimberTest {

    static Hillclimber<Boolean> hillclimberZero;
    static Hillclimber<Boolean> hillclimberOne;
    static ArrayList<Boolean> resultZ;
    static ArrayList<Boolean> resultO;

    @BeforeAll
    static void initHillclimber() {
        GenotypeBoolean genotype = new GenotypeBoolean(8);
        Fitnessfun<Boolean> fitnessfunZ = new ZeroMaxEvaluator();
        Fitnessfun<Boolean> fitnessfunO = new OneMaxEvaluator();
        Mutator<Boolean> mutator = new SwitchOneBit();
        hillclimberZero = new Hillclimber<>(genotype, fitnessfunZ, mutator);
        hillclimberOne = new Hillclimber<>(genotype, fitnessfunO, mutator);
        resultZ = new ArrayList<>();
        for(int i = 0; i<8; i++){
            resultZ.add(Boolean.FALSE);
        }
        resultO = new ArrayList<>();
        for(int i = 0; i<8; i++){
            resultO.add(Boolean.TRUE);
        }
    }

    @Test
    void testHillClimb() {
        Genotype<Boolean> expectedZ = hillclimberZero.run();
        for(int i = 0; i<8; i++){
            assertEquals(resultZ.get(i), expectedZ.valueAt(i));
        }
        Genotype<Boolean> expectedO = hillclimberOne.run();
        for(int i = 0; i<8; i++){
            assertEquals(resultO.get(i), expectedO.valueAt(i));
        }
    }
}