package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BitSetGenotypeTests {
    @Mock
    FitnessFunction<Boolean> fitnessMock;

    @Test
    public void fromString_WhenCalled_ReturnsGenotypeWithValuesMatchingString() {
        var string = "01011010";
        var genotype = BitSetGenotype.fromString(fitnessMock, string);

        var resultString = genotype.valuesToList().stream()
                .map(b -> b ? "1" : "0")
                .reduce("", (s, s2) -> s + s2);

        assertEquals(string, resultString);
    }

    @Test
    public void survive_WhenCalled_IncreasesAgeByOne() {
        var genotype = BitSetGenotype.fromString(fitnessMock, "1");

        var oldAge = genotype.getAge();
        genotype.survive();

        assertEquals(oldAge + 1, genotype.getAge());
    }
}
