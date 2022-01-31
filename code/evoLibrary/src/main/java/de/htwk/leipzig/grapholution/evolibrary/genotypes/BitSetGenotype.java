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
 * Klasse zur Darstellung eines einzelnen Genotypen unter Verwendung eines Bitsets fuer Boolean Werte
 */
public class BitSetGenotype extends Genotype<Boolean> {
    private final BitSet values;
    private final int size;

    /**
     * Konstruktor zur Erstellung eines BitSetGenotypen mit vorgegebenen Werten als BitSet
     * @param fitnessFunction Fitnessfunktion, die zur Evaluierung des Genotyps verwendet werden soll
     * @param values Werte die dem Genotyp zugewiesen werden sollen
     * @param size Groesse des Genotypen
     */
    public BitSetGenotype(FitnessFunction<Boolean> fitnessFunction, BitSet values, int size) {
        super(fitnessFunction);
        this.values = BitSet.valueOf(values.toByteArray());
        this.size = size;
        maxFitnessValue = fitnessFunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Konstruktor zur Erstellung eines BitSetGenotypen mit zufaellig generierten Werten
     * @param creator Creator fuer die zufaelligen Werte
     * @param fitnessFunction Fitnessfunktion, die zur Evaluierung des Genotyps verwendet werden soll
     * @param size Groesse des Genotypen
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
     * Funktion zur Erstellung eines BitSetGenotypen unter Verwendung vorgegebener Werte als String
     * @param fitnessFunction Fitnessfunktion, die zur Evaluierung des Genotyps verwendet werden soll
     * @param values String mit gewuenschten Werten
     * @return Erstellter BitSetGenotype
     */
    public static BitSetGenotype fromString(FitnessFunction<Boolean> fitnessFunction, String values) {
        var bitset = new BitSet(values.length());
        for (int i = 0; i < values.length(); i++) {
            if (values.charAt(i) == '1') {
                bitset.set(i);
            }
        }
        return new BitSetGenotype(fitnessFunction, bitset, values.length());
    }


    /**
     * Gibt Werte des Genotyps als Liste zurueck
     * @return Werte des Genotyps als Liste dargestellt
     */
    @Override
    public List<Boolean> valuesToList() {
        return IntStream.range(0, size)
                .mapToObj(values::get)
                .collect(Collectors.toList());
    }

    /**
     * Gibt Groesse des Genotyps zurueck
     * @return Groesse des Genotyps
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Aendert Wert an bestimmter Stelle im Genotyp
     * @param i Stelle im Genotyp die abgeaendert werden soll
     * @param element Gewuenschter Wert
     */
    @Override
    public void set(int i, Boolean element) {
        values.set(i, element);
        updateFitness();
    }


    /**
     * Gibt Wert an bestimmter Stelle im Genotyp zurueck
     * @param i Stelle im Genotyp deren Wert ermittelt werden soll
     * @return Wert an der Stelle
     */
    @Override
    public Boolean get(int i) {
        return values.get(i);
    }


    /**
     * Erzeugt eine Kopie des Genotyps
     * @return Kopie des Genotyps
     */
    @Override
    public Genotype<Boolean> createCopy() {
        return new BitSetGenotype(fitnessFunction, values, size);
    }


    /**
     * Wandelt den Genotyp in einen String um
     * @return Genotyp als String dargestellt
     */
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
     * Funktion zur Ermittlung der Anzahl an Einsen im Genotyp
     * @return Anzahl an Einsen im Genotyp
     */
    public int oneCount() {
        return values.cardinality();
    }


    /**
     * Funktion zur Ermittlung der Anzahl an Nullen im Genotyp
     * @return Anzahl an Nullen im Genotyp
     */
    public int zeroCount() {
        return size - values.cardinality();
    }


    /**
     * Flippt Bit an bestimmter Stelle im Genotyp
     * @param i Stelle an der Bit geflippt werden soll
     */
    public void flip(int i) {
        values.flip(i);
    }
}
