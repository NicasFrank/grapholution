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

public class ViewModelHillclimber{

    private BestGenotype bestGenotype;
    private Genotype<Boolean> genotype;
    private EvoLibMapper evoLibMapper;
    static ArrayList<Boolean> resultZ;
    static ArrayList<Boolean> resultO;
    static int genosize = 10;
    private final StringProperty inputField = new SimpleStringProperty();
    private final StringProperty outputField = new SimpleStringProperty();
    private final Hillclimber<Boolean> hillclimberAlgorithm;
<<<<<<< HEAD
    private boolean isOneMax;

=======

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
>>>>>>> a258c3ee017c9c74327221968b0d564ded3adc9a
    /**
     * Konstruktor
     * @param options Konfiguration Hillclimber Algorithmus
     */
    public ViewModelHillclimber(AlgorithmConfigOptions options){
        var mutator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
        var fitnessFunction = options.getBool(BoolConfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();

        genotype = BitSetGenotype.fromString(fitnessFunction, inputField.get());
        hillclimberAlgorithm = new Hillclimber<>(genotype, mutator, options);
    }

    /**
<<<<<<< HEAD
     * ruft die run Methoden des Hillclimber Algorithmus auf
     * @param
     * @return aktuell bester genotyp
=======
     * ruft die run methoden des Hillclimber algorithmus
     * @return aktuell besten genotypen
>>>>>>> a258c3ee017c9c74327221968b0d564ded3adc9a
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
     * @return ob die Eingabe 0 oder 1 ist
     */
    private boolean isInputCorrect() {
        return inputField.get().matches("[01]*");
    }

    public Property<String> outputFieldProperty() {
        return outputField;
    }

    public Property<String> inputFieldProperty() {
        return inputField;
    }
}
