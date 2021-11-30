package de.htwk.leipzig.grapholution.evolibrary.selectors;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FitnessproportionalSelectionTests {
    Selector<Integer> fitnessproportionalSelection;
    Population<Integer> testPopulation;
    FitnessFunction<Integer> testFitness;

    @BeforeEach
    void setup() {
        fitnessproportionalSelection = new FitnessproportionalSelection<>();

        testFitness = new FitnessFunction<>() {
            @Override
            public int evaluate(Genotype<Integer> genotype) {
                return genotype.get(0);
            }

            @Override
            public int getMaxFitnessValue(Genotype<Integer> genotype) {
                return Integer.MAX_VALUE;
            }
        };
    }

    @Test
    void select_WhenCalled_HasHigherChanceToSelectIndividuumWithHigherFitnessValue() {
        final int selections = 100;
        var highFitnessSelectedFirstCount = 0;

        for (int i = 0; i < selections; i++) {
            testPopulation = new Population<>(random -> random.nextInt(10), 2, 1, testFitness);

            testPopulation = fitnessproportionalSelection.select(testPopulation);

            if (testPopulation.get(0).getFitness() > testPopulation.get(1).getFitness() ) {
                highFitnessSelectedFirstCount++;
            }
        }

        assertTrue(1.0 * highFitnessSelectedFirstCount / selections > 0.5);
    }
}
