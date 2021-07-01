package main.java.workflowEval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Selector {
    private int successfulMutations;

    public Selector(int successfulMutations) {
        this.successfulMutations = successfulMutations;
    }

    public int getSuccessfulMutations() {
        return successfulMutations;
    }

    public void setSuccessfulMutations(int successfulMutations) {
        if (successfulMutations > 0) {
            this.successfulMutations = successfulMutations;
        } else {
            throw new IllegalArgumentException("Only positive Integers are accepted!");
        }
    }

    public ArrayList<Mutation> select(ArrayList<Mutation> mutations) {
        Collections.sort(mutations, Comparator.comparingInt(Mutation::getValue));

        return new ArrayList<>(mutations.subList(0, successfulMutations - 1));
    }
}