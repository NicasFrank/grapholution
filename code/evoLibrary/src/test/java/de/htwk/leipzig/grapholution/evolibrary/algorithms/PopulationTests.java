package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PopulationTests {
    Population<Integer> population;
    Genotype<Integer> testGenotype1;
    Genotype<Integer> testGenotype2;

    @Mock
    FitnessFunction<Integer> fitnessMock;

    @BeforeEach
    public void setup() {
        testGenotype1 = new Genotype<>(rand -> rand.nextInt(15), fitnessMock, 1);
        testGenotype2 = new Genotype<>(rand -> rand.nextInt(15), fitnessMock, 1);
        List<Genotype<Integer>> genotypes = new ArrayList<>();
        genotypes.add(testGenotype1);
        genotypes.add(testGenotype2);
        population = new Population<>(genotypes);
    }

    @Test
    public void getBestFitness_WhenCalled_ReturnsFitnessValueOfBestGenotype() {
        when(fitnessMock.evaluate(testGenotype1)).thenReturn(10);
        when(fitnessMock.evaluate(testGenotype2)).thenReturn(0);

        testGenotype1.updateFitness();
        testGenotype2.updateFitness();

        var result = population.getBestFitness();

        assertEquals(10, result);
    }
}
