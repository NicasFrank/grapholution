package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;

import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementierung eine Boolean-Genotypen mit einem BitSet zur Speicherung der Werte
 */
public class BitSetGenotype extends Genotype<Boolean> {
    private final BitSet values;
    private final int size;


    /**
     * Konstruktor mit den Werten aus einem BitSet
     * @param fitnessFunction Die Fitnessfunktion zur Berechnung des Fitnesswertes des Genotypen
     * @param values Die Werte, die der Genotyp haben soll
     * @param size Die Größe, die der Genotyp haben soll
     */
    public BitSetGenotype(FitnessFunction<Boolean> fitnessFunction, BitSet values, int size) {
        super(fitnessFunction);
        this.values = BitSet.valueOf(values.toByteArray());
        this.size = size;
        maxFitnessValue = fitnessFunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Konstruktor mit Werten, die von der übergebenen Funktion generiert werden
     * @param creator Funktion zur Generierung der Werte des Genotyps
     * @param fitnessFunction Die Fitnessfunktion zur Berechnung des Fitnesswertes des Genotypen
     * @param size Die Größe, die der Genotyp haben soll
     */
    public BitSetGenotype(Function<Random, Boolean> creator, FitnessFunction<Boolean> fitnessFunction, int size) {
        super(fitnessFunction);
        this.size = size;
        values = new BitSet(size);
        for (int i = 0; i < size; i++) {
            values.set(i, creator.apply(ThreadLocalRandom.current()));
        }
        maxFitnessValue = fitnessFunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Erstellt eine neue Instanz von BitSetGenotype mit den Werten im übergebenen String
     * @param fitnessFunction Die Fitnessfunktion zur Berechnung des Fitnesswertes des Genotypen
     * @param values Die Werte, die der Genotyp haben soll
     * @return Die neue Instanz von BitSetGenotype
     */
    public static BitSetGenotype fromString(FitnessFunction<Boolean> fitnessFunction, String values) {
        var bitset = new BitSet(values.length());

        IntStream.range(0, values.length())
                .filter(i -> values.charAt(i) == '1')
                .forEach(bitset::set);
        
        return new BitSetGenotype(fitnessFunction, bitset, values.length());
    }

    @Override
    public List<Boolean> valuesToList() {
        return IntStream.range(0, size)
                .mapToObj(values::get)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int i, Boolean element) {
        values.set(i, element);
        updateFitness();
    }

    @Override
    public Boolean get(int i) {
        return values.get(i);
    }

    @Override
    public Genotype<Boolean> createCopy() {
        return new BitSetGenotype(fitnessFunction, values, size);
    }

    @Override
    public String toString() {
        return IntStream
                .range(0, size)
                .mapToObj(i -> values.get(i) ? '1' : '0')
                .collect(
                        () -> new StringBuilder(size),
                        StringBuilder::append,
                        StringBuilder::append
                )
                .toString();
    }

    /**
     * Gibt die Anzahl der gesetzten Bits zurück
     * @return Die Anzahl der gesetzten Bits
     */
    public int oneCount() {
        return values.cardinality();
    }

    /**
     * Gibt die Anzahl der nicht gesetzten Bits zurück
     * @return Die Anzahl der nicht gesetzten Bits
     */
    public int zeroCount() {
        return size - values.cardinality();
    }

    /**
     * Invertiert den Wert des Bits an der Stelle i
     * @param i Die Stelle des Bits, das invertiert werden soll
     */
    public void flip(int i) {
        values.flip(i);
    }
}
