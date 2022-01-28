package de.htwk.leipzig.grapholution.evolibrary.statistics;


import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ColorBitString {
    private final List<BitDoublePair> data;

    public ColorBitString(Population<Boolean> population) {
        data = IntStream.range(0, population.get(0).size())
                .mapToObj(i -> new BitDoublePair(
                        population.getBestIndividual().get(i),
                        population.stream()
                                .filter(g -> g.get(i) == g.getFitnessTarget())
                                .count() * 1.0 / population.size()
                ))
                .collect(Collectors.toList());
    }

    public BitDoublePair get(int i) {return data.get(i);}

    public int size() {
        return data.size();
    }

    public static class BitDoublePair {
        private final boolean b;
        private final double d;

        public BitDoublePair(boolean b, double d) {
            this.b = b;
            this.d = d;
        }

        public boolean getBit() {
            return b;
        }

        public double getDouble() {
            return d;
        }
    }
}
