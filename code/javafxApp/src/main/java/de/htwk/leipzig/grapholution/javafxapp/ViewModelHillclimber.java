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
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class ViewModelHillclimber{

    private Genotype<Boolean> genotype;
    private final StringProperty inputField = new SimpleStringProperty();
    private final StringProperty outputField = new SimpleStringProperty();
    private final Hillclimber<Boolean> hillclimberAlgorithm;

    /**
     * Konstruktor
     * @param options Konfiguration Hillclimber Algorithmus
     */
    public ViewModelHillclimber(AlgorithmConfigOptions options,SceneControllerHillclimber SCh){
        inputField.bindBidirectional(SCh.getInputField().textProperty());
        var mutator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
        var fitnessFunction = options.getBool(BoolConfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
        boolean isOneMax = options.getBool(BoolConfig.FitnessIsOneMax);
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
     */

    public List<HillModel> runAlgorithm(){
        Genotype<Boolean> bitGeno = hillclimberAlgorithm.run();
        outputField.set(bitGeno.toString());
        return makeListHillModel();
    }

    /**
     * @TODO wie ist das mit den iterations, wird ja nicht immer in die stats geschrieben, woher die richtige iteration?
     * @return
     */
    private List<HillModel> makeListHillModel(){
        List<HillModel> listHM = new ArrayList<>();
        List<Genotype<Boolean>> stats = hillclimberAlgorithm.getStatistics().getBestIndividuals();
        for ( int i = 0; i<stats.size();i++){
            listHM.add(new HillModel(i,stats.get(i)));
        }
        return listHM;
    }
    /**
     * @TODO isInputCorrect an richtiger Stelle einfügen
     * Methode zum überprüfen ob Eingabe 0 oder 1
     * @return ob die Eingabe 0 oder 1 ist
     */
    private boolean isInputCorrect() {
        return inputField.get().matches("[01]*");
    }

    public StringProperty outputFieldProperty() {
        return outputField;
    }

    public StringProperty inputFieldProperty() {
        return inputField;
    }

    public Statistics<Boolean> getHillclimberStatistic(){
        return hillclimberAlgorithm.getStatistics();
    }
}
