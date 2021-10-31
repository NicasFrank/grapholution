package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.OnePointCrossover;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OnePointCrossoverTests {
    Recombinator<Integer> onePointCrossover;
    Genotype<Integer> testGenotype1;
    List<Integer> testValues1;
    Genotype<Integer> testGenotype2;
    List<Integer> testValues2;

    @Mock
    Fitnessfunction<Integer> fitnessMock;

    @BeforeEach
    private void setup() {
        testValues1 = new ArrayList<>();
        testValues1.add(1);
        testValues1.add(2);
        testGenotype1 = new Genotype<>(fitnessMock, testValues1);
        testValues2 = new ArrayList<>();
        testValues2.add(3);
        testValues2.add(4);
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
