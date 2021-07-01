package main.java.workflowEval;

public class Mutation {
    private String state;
    private int value;

    public Mutation(String state, int value) {
        this.state = state;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}