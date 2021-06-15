package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBoolean;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
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
        Fitnessfunction<Boolean> fitnessfunctionZ = new ZeroMaxEvaluator();
        Fitnessfunction<Boolean> fitnessfunctionO = new OneMaxEvaluator();
        GenotypeBoolean genotypeZ = new GenotypeBoolean(fitnessfunctionZ, 5);
        GenotypeBoolean genotypeO = new GenotypeBoolean(fitnessfunctionO, 5);
        Mutator<Boolean> mutatorB = new BinaryMutation(10);
        Mutator<Boolean> mutatorS = new SwitchOneBit();
        hillclimberZero = new Hillclimber<>(genotypeZ, mutatorB);
        hillclimberOne = new Hillclimber<>(genotypeO, mutatorB);
        resultZ = new ArrayList<>();
        for(int i = 0; i<5; i++){
            resultZ.add(Boolean.FALSE);
        }
        resultO = new ArrayList<>();
        for(int i = 0; i<5; i++){
            resultO.add(Boolean.TRUE);
        }
    }

    @Test
    void testHillClimbZero() {
        Genotype<Boolean> expectedZ = hillclimberZero.run();
        for(int i = 0; i<5; i++){
            assertEquals(resultZ.get(i), expectedZ.valueAt(i));
        }
    }
    @Test
    void testHillClimbOne() {
        Genotype<Boolean> expectedO = hillclimberOne.run();
        for(int i = 0; i<5; i++){
            assertEquals(resultO.get(i), expectedO.valueAt(i));
        }
    }

}