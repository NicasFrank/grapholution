package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.ListGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ColorBitStringTests {
    @Mock
    FitnessFunction<Boolean> fitnessMock;

    @Test
    public void Constructor_WhenCalled_CreatesColorBitStringWithValuesBasedOnGenotypes() {
        when(fitnessMock.target()).thenReturn(true);
        var population = new Population<>(List.of(
                new ListGenotype<>(fitnessMock, List.of(true, true, false)),
                new ListGenotype<>(fitnessMock, List.of(true, false, false))
        ));

        var colorBitString = new ColorBitString(population);

        assert colorBitString.size() == 3;

        assert colorBitString.get(0).getBit();
        assert colorBitString.get(0).getDouble() == 1.0;

        assert colorBitString.get(1).getBit();
        assert colorBitString.get(1).getDouble() == 0.5;

        assert !colorBitString.get(2).getBit();
        assert colorBitString.get(2).getDouble() == 0;
    }
}
