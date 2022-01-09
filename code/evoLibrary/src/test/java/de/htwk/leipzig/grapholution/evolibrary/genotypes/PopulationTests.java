package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PopulationTests {
    Population<Integer> population;
    Genotype<Integer> testGenotype1;
    Genotype<Integer> testGenotype2;

    @Mock
    FitnessFunction<Integer> fitnessMock;
    private List<Genotype<Integer>> genotypes;

    @BeforeEach
    public void setup() {
        testGenotype1 = new ListGenotype<>(rand -> rand.nextInt(15), fitnessMock, 1);
        testGenotype2 = new ListGenotype<>(rand -> rand.nextInt(15), fitnessMock, 1);
        genotypes = new ArrayList<>();
        genotypes.add(testGenotype1);
        genotypes.add(testGenotype2);
    }

    @Test
    public void getBestFitness_WhenCalled_ReturnsFitnessValueOfBestGenotype() {
        when(fitnessMock.evaluate(any())).thenReturn(10);
        testGenotype1.updateFitness();
        when(fitnessMock.evaluate(any())).thenReturn(0);
        testGenotype2.updateFitness();

        population = new Population<>(genotypes);

        var result = population.getBestFitness();

        assertEquals(10, result);
    }

    @Test
    public void createCopy_WhenCalled_ReturnsPopulationWithMatchingGenotypes() {
        population = new Population<>(rand -> rand.nextInt(15), 10, 10, fitnessMock);

        var copy = population.createCopy();

        for (int i = 0; i < population.size(); i++) {
            for(int j = 0; j<population.get(i).size(); j++){
                assertEquals(population.get(i).get(j), copy.get(i).get(j));
            }
        }
    }
}
