package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PopulationTests {
    Population population;
    Genotype<Integer> testGenotype1;
    Genotype<Integer> testGenotype2;

    @Mock
    Fitnessfunction<Integer> fitnessMock;

    @BeforeEach
    public void setup() {
        testGenotype1 = new Genotype<>(fitnessMock, 1);
        testGenotype2 = new Genotype<>(fitnessMock, 1);
        population = new Population();
        population.add(testGenotype1);
        population.add(testGenotype2);
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
