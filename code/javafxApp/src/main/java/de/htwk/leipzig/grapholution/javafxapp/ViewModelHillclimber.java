package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Konstruktor
     * @param mutatorIsBinary ob die mutation binary ist wenn nicht switch one bit
     * @param mutationChance die wahrscheinlichkeit der binary mutation
     * @param fitnessIsOneMax ob OneMax verwendet wird
     */

/*
    public ViewModelHillclimber(boolean mutatorIsBinary, double mutationChance, boolean fitnessIsOneMax){
        FitnessFunction<Boolean> fitnessfunctionZ = new ZeroMaxEvaluator();
        FitnessFunction<Boolean> fitnessfunction0 = new OneMaxEvaluator();
        Genotype<Boolean> genotypeZ = new Genotype<>(Random::nextBoolean, fitnessfunctionZ, genosize);
        Genotype<Boolean> genotypeO = new Genotype<>(r -> false, fitnessfunction0, genosize);
        Mutator<Boolean> mutatorB = new BinaryMutation(10);
        Mutator<Boolean> mutatorS = new SwitchOneBit();
        hillclimberZero = new Hillclimber<Boolean>(genotypeZ, mutatorB);
        hillclimberOne = new Hillclimber<Boolean>(genotypeO, mutatorS);
        hillclimberOneLimit = new Hillclimber<Boolean>(genotypeO, mutatorS, 8);
        resultZ = new ArrayList<>();
        for(int i = 0; i<genosize; i++){
            resultZ.add(Boolean.FALSE);
        }
        runAlgorithm(false);
        resultO = new ArrayList<>();
        for(int i = 0; i<genosize; i++){
            resultO.add(Boolean.TRUE);
        }
        runAlgorithm(false);
    }

    /**
     * ruft die run methoden des Hillclimber algorithmus
     * @param untilDone ob der algorithmus bis zur maximalanzahl der generationen oder bestmoeglichen individuums durchlaufen soll
     * @return aktuell besten genotypen
     */
/*
    public BestGenotype runAlgorithm(boolean untilDone){
        if(!untilDone) {
            isInputCorrect();
            int fitnessZ = hillclimberZero.run().getFitness();
            int ageZ = hillclimberZero.run().getAge();
            hillclimberZero.run();
            bestGenotype = new BestGenotype(fitnessZ,ageZ);
            System.out.println(bestGenotype +" Iteration: " + hillclimberZero.getIterations());
        }
        else{
            isInputCorrect();
            int fitness0 = hillclimberOne.run().getFitness();
            int age0 = hillclimberOne.run().getAge();
            hillclimberOne.run();
            bestGenotype = new BestGenotype(fitness0,age0);
            System.out.println(bestGenotype +" Iteration: " + hillclimberOne.getIterations());
        }

        return bestGenotype;
    }
*/
    /**
     * Konstruktor
     * @param mutatorIsBinary ob die mutation binary ist wenn nicht switch one bit
     * @param mutationChance die wahrscheinlichkeit der binary mutation
     * @param fitnessIsOneMax ob OneMax verwendet wird
     */
    public ViewModelHillclimber(AlgorithmConfigOptions options){
        var mutatator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
        var fitnessFunction = options.getBool(BoolCinfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
        hillclimberAlgorithm = new HillclimberAlgorithm<>(mutator,options);
    }

    /**
     * ruft die run methoden des Hillclimber algorithmus
     * @param untilDone ob der algorithmus bis zur maximalanzahl der generationen oder bestmoeglichen individuums durchlaufen soll
     * @return aktuell besten genotypen
     */
    public BestGenotype runAlgorithm(){
        if(BoolfConfig.FitnessIsOneMax){
            int fitness = hillclimberOne.run.getFitness();
            int age = hillclimberOne.run.getAge();
            bestGenotype = new BestGenotype(fitness,age);
            System.out.println(bestGenotype + "Iteration: " + hillclimberAlgorithm.getIterations);
        }
        if(BoolConfig.FitnessIsZeroMax){
            int fitness = hillclimberZero.run.getFitness();
            int age = hillclimberZero.run.getAge();
            bestGenotype = new BestGenotype(fitness,age);
            System.out.println(bestGenotype + "Iteration: " + hillclimberAlgorithm.getIterations);
        }

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
