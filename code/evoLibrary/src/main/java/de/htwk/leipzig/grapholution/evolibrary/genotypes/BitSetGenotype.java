package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;

import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BitSetGenotype extends Genotype<Boolean> {
    private final BitSet values;

    public BitSetGenotype(FitnessFunction<Boolean> fitnessFunction, BitSet values) {
        super(fitnessFunction);
        this.values = BitSet.valueOf(values.toByteArray());
    }

    public BitSetGenotype(FitnessFunction<Boolean> fitnessFunction, Function<Random, Boolean> creator, int size) {
        super(fitnessFunction);
        values = new BitSet(size);
        for (int i = 0; i < size; i++) {
            values.set(i, creator.apply(ThreadLocalRandom.current()));
        }
    }

    @Override
    public List<Boolean> valuesToList() {
        return Stream.iterate(0, i -> i + 1)
                .limit(values.size())
                .map(values::get)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public void set(int i, Boolean element) {
        values.set(i, element);
    }

    @Override
    public Boolean get(int i) {
        return values.get(i);
    }

    @Override
    public Genotype<Boolean> createCopy() {
        return new BitSetGenotype(fitnessFunction, values);
    }

    @Override
    public void print() {

    }
}
