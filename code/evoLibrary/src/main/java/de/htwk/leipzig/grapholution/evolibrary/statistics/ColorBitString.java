package de.htwk.leipzig.grapholution.evolibrary.statistics;


import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Klasse zur Speicherung der Prozentanteil der Stellen der Genotypen in der Population, zur farblichen Darstellung
 */
public class ColorBitString {
    private final List<BitDoublePair> data;

    /**
     * Konstruktor
     * @param population Die Population, für die die Prozentanteile berechnet werden sollen
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
     * Gibt den Bitwert des besten Individuums und den Prozentanteil an der übergebenen Stelle zurück
     * @param i Die Stelle, dessen Daten zurückgeben werden sollen
     * @return Den Bitwert des besten Individuums und den Prozentanteil an der übergebenen Stelle
     */
    public BitDoublePair get(int i) {return data.get(i);}

    /**
     * Gibt die Anzahl der gespeicherten Prozentwerte zurück
     * @return Die Anzahl der gespeicherten Prozentwerte
     */
    public int size() {
        return data.size();
    }

    /**
     * Klasse zur gemeinsamen Speicherung eines Bitwerts und einer Kommazahl
     */
    public static class BitDoublePair {
        private final boolean b;
        private final double d;

        /**
         * Konstruktor aus einem Bitwert und einer Kommazahl
         * @param b Der zu speichernde Bitwert
         * @param d Die zu speichernde Kommazahl
         */
        public BitDoublePair(boolean b, double d) {
            this.b = b;
            this.d = d;
        }

        /**
         * Gibt den Bitwert zurück
         * @return Den Bitwert
         */
        public boolean getBit() {
            return b;
        }

        /**
         * Gibt die Kommazahl zurück
         * @return Die Kommazahl
         */
        public double getDouble() {
            return d;
        }
    }
}
