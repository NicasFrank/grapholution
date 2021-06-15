package de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfun;
import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

import java.util.ArrayList;


public class Hillclimber<T> extends Algorithm<T> {

    private final ArrayList<Genotype<T>> history;

    public Hillclimber(Genotype<T> genotype, Fitnessfun<Genotype<T>> fitnessfun, Mutator<Genotype<T>> mutator) {
        super(genotype, fitnessfun, mutator);
        history = new ArrayList<>();
        history.add(genotype);
    }

    private Genotype<T> getLastConfig() {
        return history.get(history.size() - 1);
    }

    public Genotype<T> run() {
        while (fitnessfun.evaluate(getLastConfig()) < getLastConfig().length()) {
            Genotype<T> copy = getLastConfig().createCopy();
            mutator.mutate(copy);
            int eval = fitnessfun.evaluate(copy);
            if (eval > fitnessfun.evaluate(getLastConfig())) {
                history.add(copy);
            }
            else {
                getLastConfig().survive();
            }
        }
        return getLastConfig();
    }

}
