package de.htwk.leipzig.grapholution.evolibrary.statistics;


import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Klasse zur Veranschaulichung der Verteilung bzw. Haeufigkeit von Werten ueber eine Population
 */
public class ColorBitString {
    private final List<BitDoublePair> data;

    /**
     * Konstruktor zur Erstellung einer ColorBitString Klasse
     * @param population Population, die evaluiert werden soll
     */
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

    /**
     * Getter fuer Wertpaar des besten Individuums einer population
     * @param i Stelle des Individuums dessen Wertpaar gewuenscht ist
     * @return Wertpaar mit boolean die den Wert wiedergibt und double die die Haeufigkeit darstellt
     */
    public BitDoublePair get(int i) {return data.get(i);}

    /**
     * Getter fuer Anzahl der Wertpaare
     * @return Anzahl der Wertpaare
     */
    public int size() {
        return data.size();
    }

    /**
     * Klasse die ein Paar eines boolean und eines double Wertes darstellt
     */
    public static class BitDoublePair {
        private final boolean b;
        private final double d;

        /**
         * Konstruktor zur Erstellung eines Paares
         * @param b boolean Wert
         * @param d double Wert
         */
        public BitDoublePair(boolean b, double d) {
            this.b = b;
            this.d = d;
        }

        /**
         * Getter fuer Boolean Wert
         * @return Boolean wert
         */
        public boolean getBit() {
            return b;
        }

        /**
         * Getter fuer double Wert
         * @return double wert
         */
        public double getDouble() {
            return d;
        }
    }
}
