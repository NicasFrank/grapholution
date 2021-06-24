package de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

import java.util.ArrayList;
import java.util.List;


public class Hillclimber<T> extends Algorithm<T> {

    private final List<Genotype<T>> history;

    public Hillclimber(Genotype<T> genotype, Mutator<T> mutator) {
        super(genotype, mutator);
        history = new ArrayList<>();
        history.add(genotype);
    }

    public Hillclimber(Genotype<T> genotype, Mutator<T> mutator, int limit) {
        super(genotype, mutator, limit);
        history = new ArrayList<>();
        history.add(genotype);
    }

    private Genotype<T> getLastConfig() {
        return history.get(history.size() - 1);
    }

    public Genotype<T> run() {
            while (getLastConfig().getFitness() < getLastConfig().MAX_FITNESS_VALUE &&
                (limit < 0 || iterations < limit)){
            Genotype<T> copy = getLastConfig().createCopy();
            mutator.mutate(copy);
            if (copy.getFitness() > getLastConfig().getFitness()) {
                history.add(copy);
            }
            else {
                getLastConfig().survive();
            }
            iterations++;
        }
        return getLastConfig();
    }

}
