package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class HillclimberTest {

    static Hillclimber<Boolean> hillclimberZero;
    static Hillclimber<Boolean> hillclimberOne;
    static Hillclimber<Boolean> hillclimberOneLimit;
    static ArrayList<Boolean> resultZ;
    static ArrayList<Boolean> resultO;
    static int genosize = 10;

    @BeforeAll
    static void initHillclimber() {
        FitnessFunction<Boolean> fitnessfunctionZ = new ZeroMaxEvaluator();
        FitnessFunction<Boolean> fitnessfunctionO = new OneMaxEvaluator();
        Genotype<Boolean> genotypeZ = new Genotype<>(Random::nextBoolean, fitnessfunctionZ, genosize);
        Genotype<Boolean> genotypeO = new Genotype<>(r -> false, fitnessfunctionO, genosize);
        Mutator<Boolean> mutatorB = new BinaryMutation(10);
        Mutator<Boolean> mutatorS = new SwitchOneBit();
        hillclimberZero = new Hillclimber<>(genotypeZ, mutatorB);
        hillclimberOne = new Hillclimber<>(genotypeO, mutatorS);
        hillclimberOneLimit = new Hillclimber<>(genotypeO, mutatorS, 8);
        resultZ = new ArrayList<>();
        for(int i = 0; i<genosize; i++){
            resultZ.add(Boolean.FALSE);
        }
        resultO = new ArrayList<>();
        for(int i = 0; i<genosize; i++){
            resultO.add(Boolean.TRUE);
        }
    }

    @Test
    void testHillClimbZero() {
        Genotype<Boolean> expectedZ = hillclimberZero.run();
        for(int i = 0; i<genosize; i++){
            assertEquals(resultZ.get(i), expectedZ.get(i));
        }
    }
    @Test
    void testHillClimbOne() {
        Genotype<Boolean> expectedO = hillclimberOne.run();
        for(int i = 0; i<genosize; i++){
            assertEquals(resultO.get(i), expectedO.get(i));
        }
    }
    @Test
    void testHillClimberLimit() {
        hillclimberOneLimit.run();
        assertEquals(8, hillclimberOneLimit.getIterations());
    }


}