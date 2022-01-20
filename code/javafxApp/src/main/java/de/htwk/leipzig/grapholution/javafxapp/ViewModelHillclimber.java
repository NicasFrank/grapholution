package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.BitSet;

public class ViewModelHillclimber{

    private BestGenotype bestGenotype;
    private Genotype<Boolean> genotype;
    private EvoLibMapper evoLibMapper;
    private static Hillclimber<Boolean> hillclimberOne;
    private static Hillclimber<Boolean> hillclimberZero;
    private static Hillclimber<Boolean> hillclimberOneLimit;
    static ArrayList<Boolean> resultZ;
    static ArrayList<Boolean> resultO;
    static int genosize = 10;
    private final StringProperty inputField = new SimpleStringProperty();
    private final StringProperty outputField = new SimpleStringProperty();
    private final Hillclimber<Boolean> hillclimberAlgorithm;
    private boolean isOneMax;

    /**
     * Konstruktor
     * @param options Konfiguration Hillclimber Algorithmus
     */
    public ViewModelHillclimber(AlgorithmConfigOptions options){
        var mutator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
        var fitnessFunction = options.getBool(BoolConfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
        isOneMax = options.getBool(BoolConfig.FitnessIsOneMax);
        var bitset = new BitSet(inputField.get().length());
        for (int i = 0; i < inputField.get().length(); i++) {
            if (inputField.get().charAt(i) == '1') {
                bitset.set(i);
            }
        }
        genotype = new BitSetGenotype(fitnessFunction,bitset,inputField.get().length());
        hillclimberAlgorithm = new Hillclimber<Boolean>(genotype, mutator, options);
    }

    /**
     * ruft die run Methoden des Hillclimber Algorithmus auf
     * @param
     * @return aktuell bester genotyp
     */
    public BestGenotype runAlgorithm(){
            isInputCorrect();
            int fitness = hillclimberAlgorithm.run().getFitness();
            int age = hillclimberAlgorithm.run().getAge();
            bestGenotype = new BestGenotype(fitness,age);
            System.out.println(bestGenotype + "Iteration: " + hillclimberAlgorithm.getIterations());

        return bestGenotype;
    }


    /**
     * Methode zum überprüfen ob Eingabe 0 oder 1
     * @return true falls korrekt
     * @return false falls inkorrekt
     */
    private boolean isInputCorrect() {
        char[] input = inputField.get().toCharArray();
        for (char c : input) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    public Property<String> outputFieldProperty() {
        return outputField;
    }

    public Property<String> inputFieldProperty() {
        return inputField;
    }
}
