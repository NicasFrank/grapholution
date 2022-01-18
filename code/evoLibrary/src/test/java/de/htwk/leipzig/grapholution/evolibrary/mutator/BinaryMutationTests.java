package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.ListGenotype;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BinaryMutationTests {
    Mutator<Boolean> binaryMutation;
    Genotype<Boolean> testGenotype;
    @Mock
    FitnessFunction<Boolean> fitnessMock;

    @BeforeEach
    void setup() {
        testGenotype = new BitSetGenotype(Random::nextBoolean, fitnessMock, 10);
    }

    @Test
    public void mutate_WhenCalled_FlipsBitsIfProbabilityIsHighEnough() {
        var copy = testGenotype.createCopy();
        binaryMutation = new BinaryMutation(100);

        testGenotype = binaryMutation.mutate(testGenotype);

        for (int i = 0; i < testGenotype.size(); i++) {
            assertEquals(copy.get(i), !testGenotype.get(i));
        }
    }

    @Test
    public void mutate_WhenCalled_DoesNotFlipBitsIfProbabilityIsNotHighEnough() {
        var copy = testGenotype.createCopy();
        binaryMutation = new BinaryMutation(0);

        testGenotype = binaryMutation.mutate(testGenotype);

        for (int i = 0; i < testGenotype.size(); i++) {
            assertEquals(copy.get(i), testGenotype.get(i));
        }
    }
}
