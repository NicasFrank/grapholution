package de.htwk.leipzig.grapholution.javafxapp.viewModel;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
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
import de.htwk.leipzig.grapholution.javafxapp.models.HillModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel-Klasse für den Hillclimber
 */
public class ViewModelHillclimber{

    private final StringProperty outputField = new SimpleStringProperty();
    private final Hillclimber<Boolean> hillclimberAlgorithm;

    /**
     * Konstruktor
     * @param options Konfiguration Hillclimber Algorithmus
     * @param input Die Werte des Genotypen, mit dem der Hillclimber starten soll
     */
    public ViewModelHillclimber(AlgorithmConfigOptions options, String input){
        if (!isInputCorrect(input)) {
            throw new InvalidParameterException("The input must only contain 1 and 0!");
        }

        var mutator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
        var fitnessFunction = options.getBool(BoolConfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();

        var genotype = BitSetGenotype.fromString(fitnessFunction, input);
        hillclimberAlgorithm = new Hillclimber<>(genotype, mutator, options);
    }

    /**
     * ruft die run Methode des Hillclimbers auf
     */
    public List<HillModel> runAlgorithm(){
        Genotype<Boolean> bitGeno = hillclimberAlgorithm.run();
        outputField.set(bitGeno.toString());
        return makeListHillModel();
    }

    /**
     * Gibt eine HillModel-Liste aus den Statistiken des Hillclimbers zurück
     * @return Die HillModel-Liste
     */
    private List<HillModel> makeListHillModel(){
        var listHM = new ArrayList<HillModel>();
        var stats = hillclimberAlgorithm.getStatistics().getBestIndividuals();

        for ( int i = 0; i<stats.size();i++){
            listHM.add(new HillModel(i,stats.get(i)));
        }

        return listHM;
    }
    /**
     * Methode zum Überprüfen ob Eingabe 0 oder 1
     * @return ob die Eingabe 0 oder 1 ist
     */
    private boolean isInputCorrect(String input) {
        return input.matches("[01]*");
    }

    /**
     * Gibt die StringProperty des Ausgabefeldes zurück
     * @return Die StringProperty des Ausgabefeldes
     */
    public StringProperty outputFieldProperty() {
        return outputField;
    }

    /**
     * Gibt die Statistiken des Hillclimbers zurück
     * @return Die Statistiken des Hillclimbers
     */
    public Statistics<Boolean> getHillclimberStatistic(){
        return hillclimberAlgorithm.getStatistics();
    }
}
