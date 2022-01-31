package de.htwk.leipzig.grapholution.javafxapp.viewModel;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.geneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.OnePointCrossover;
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.models.GenModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ViewModel-Klasse für den genetischen Algorithmus
 */
public class ViewModelGeneticAlgorithm{

  private final GeneticAlgorithm<Boolean> geneticAlgorithm;

  /**
   * Erstellt anhand der Einstellungswerte den gewünschten genetischen Algorithmus
   * @param options Die Einstellungswerte, mit denen der genetische Algorithmus erstellt werden soll
   */
  public ViewModelGeneticAlgorithm(AlgorithmConfigOptions options){
    var mutator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
    var selector= new FitnessproportionalSelection<Boolean>();
    var recombinator = new OnePointCrossover<Boolean>();
    var fitnessFunction = options.getBool(BoolConfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
    var population = new Population<>(
            () -> new BitSetGenotype(Random::nextBoolean, fitnessFunction, options.getInt(IntConfig.GenotypeSize)),
            options.getInt(IntConfig.PopulationSize));
    geneticAlgorithm = new GeneticAlgorithm<>(mutator, selector, recombinator, population, options);
  }

  /**
   * Führt einen Schritt des genetischen Algorithmus aus und gibt das Ergebnis davon als GenModel zurück
   * @return aktuell besten Genotypen als GenModel
   */
  public GenModel runAlgorithm(){
    Genotype<Boolean> bestGenotype = geneticAlgorithm.oneStep();
    Statistics<Boolean> stats = geneticAlgorithm.getStatistics();
    return new GenModel(geneticAlgorithm.getIterations(), bestGenotype,stats.getHistory().get(stats.getBestIndividuals().size()-1));
  }

  /**
   * Führt den genetischen Algorithmus vollständig aus und gibt das Ergebnis davon als GenModel-Liste zurück
   * @return Das Ergebnis des genetischen Algorithmus als GenModel-Liste
   */
  public List<GenModel> finishAlgorithm(){
    geneticAlgorithm.run();
    return  makeGenModelList(geneticAlgorithm.getStatistics());
  }

  private List<GenModel> makeGenModelList(Statistics<Boolean> stats){
    int size = stats.getBestIndividuals().size();
    List<GenModel> genModelList = new ArrayList<>();
    for (int i=0; i<size;i++){
      genModelList.add(new GenModel(i,stats.getBestIndividuals().get(i),stats.getHistory().get(i)));
    }
    return genModelList;
  }

  /**
   * Gibt die Statistiken des genetischen Algorithmus zurück
   * @return Die Statistiken des genetischen Algorithmus
   */
  public Statistics<Boolean> getGeneticAlgorithmStatistic(){
    return geneticAlgorithm.getStatistics();
  }
}
