package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.List;

public class EvoLibMapper {
    public List<HistoryResults> map(List<Genotype<Boolean>> results) {
        return null; // TODO
    }

    public BestGenotype map(Genotype<Boolean> genotype) {
        return new BestGenotype(genotype.getFitness(), genotype.getAge());
    }
}
