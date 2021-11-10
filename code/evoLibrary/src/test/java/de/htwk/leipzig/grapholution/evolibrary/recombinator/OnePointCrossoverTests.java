package de.htwk.leipzig.grapholution.evolibrary.recombinator;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OnePointCrossoverTests {
    Recombinator<Integer> onePointCrossover;
    Genotype<Integer> testGenotype1;
    List<Integer> testValues1;
    Genotype<Integer> testGenotype2;
    List<Integer> testValues2;

    @Mock
    FitnessFunction<Integer> fitnessMock;

    @BeforeEach
    private void setup() {
        testValues1 = new ArrayList<>();
        testValues1.add(1);
        testValues1.add(2);
        testValues1.add(3);
        testValues1.add(4);
        System.out.println(testValues1.size());
        testGenotype1 = new Genotype<>(fitnessMock, testValues1);
        testValues2 = new ArrayList<>();
        testValues2.add(5);
        testValues2.add(6);
        testValues2.add(7);
        testValues2.add(8);
        testGenotype2 = new Genotype<>(fitnessMock, testValues2);
        onePointCrossover = new OnePointCrossover<>();
    }

    @Test
    void recombine_WhenCalled_SwapsAtLeastTwoValuesBetweenTheGenotypes() {
        when(fitnessMock.evaluate(any(Genotype.class))).thenReturn(1);

        onePointCrossover.recombine(testGenotype1, testGenotype2);

        assertFalse(testGenotype1.getValues().containsAll(testValues1));
        assertFalse(testGenotype2.getValues().containsAll(testValues2));
    }
}
