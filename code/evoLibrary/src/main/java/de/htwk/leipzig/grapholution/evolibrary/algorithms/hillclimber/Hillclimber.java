package de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

import java.util.ArrayList;


public class Hillclimber<T> extends Algorithm<T> {

    private final ArrayList<Genotype<T>> history;

    public Hillclimber(Genotype<T> genotype, Mutator<T> mutator) {
        super(genotype, mutator);
        history = new ArrayList<>();
        history.add(genotype);
    }

    private Genotype<T> getLastConfig() {
        return history.get(history.size() - 1);
    }

    public Genotype<T> run() {
        while (getLastConfig().getFitness() < getLastConfig().length()) {
            Genotype<T> copy = getLastConfig().createCopy();
            mutator.mutate(copy);
            if (copy.getFitness() > getLastConfig().getFitness()) {
                copy.print();
                history.add(copy);
            }
            else {
                getLastConfig().print();
                getLastConfig().survive();
            }
        }
        return getLastConfig();
    }

}
