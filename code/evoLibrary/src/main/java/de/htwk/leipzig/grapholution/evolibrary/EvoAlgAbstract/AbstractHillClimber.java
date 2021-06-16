package de.htwk.leipzig.grapholution.evolibrary.EvoAlgAbstract;

import java.util.ArrayList;

public abstract class AbstractHillClimber<T> implements IEvoAlg<T> {
    private ArrayList<T> history;

    public AbstractHillClimber() {
        history = new ArrayList<>();
    }

    @Override
    public T run(T startConfig) {
        history.add(startConfig);

        return null;
    }


}
