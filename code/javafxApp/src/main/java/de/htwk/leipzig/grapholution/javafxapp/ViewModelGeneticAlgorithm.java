package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.ZeroMaxEvaluator;
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
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import de.htwk.leipzig.grapholution.javafxapp.model.HistoryResults;
import de.htwk.leipzig.grapholution.javafxapp.model.TableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.StreamSupport.stream;

public class ViewModelGeneticAlgorithm{

  private final GeneticAlgorithm<Boolean> geneticAlgorithm;
  private boolean isStepByStep;


  /**
   * erstellt anhand der Konfiguration den gewuenschten genetischen Algorithmus
   * loest erste iteratation des genetischen algorithmus aus
   */
  public ViewModelGeneticAlgorithm(AlgorithmConfigOptions options){
    this.isStepByStep = options.getBool(BoolConfig.IsStepByStep);
    var mutator = options.getBool(BoolConfig.MutationIsBinary) ? new BinaryMutation(options.getInt(IntConfig.MutationChance)) : new SwitchOneBit();
    var selector= new FitnessproportionalSelection<Boolean>();
    var recombinator = new OnePointCrossover<Boolean>();
    var fitnessFunction = options.getBool(BoolConfig.FitnessIsOneMax) ? new OneMaxEvaluator() : new ZeroMaxEvaluator();
    var population = new Population<>(Random::nextBoolean,options.getInt(IntConfig.PopulationSize),options.getInt(IntConfig.GenotypeSize),fitnessFunction);
    geneticAlgorithm = new GeneticAlgorithm<>(mutator, selector, recombinator, population, options);
  }

  /**
   * ruft die run methoden des genetischen algorithmus abheangig ob schrittweise oder auf einmal gerechnet werden soll
   * @return aktuell besten genotypen
   */
  public TableModel runAlgorithm(){
    geneticAlgorithm.oneStep();
    Statistics<Boolean> stats = geneticAlgorithm.getStatistics();
    int statsSize = stats.getBestIndividuals().size()-1;
    return new TableModel(geneticAlgorithm.getIterations(), stats.getBestIndividuals().get(statsSize),stats.getHistory().get(statsSize));
  }
  public List<TableModel> finishAlgorithm(){
    geneticAlgorithm.run();
    return  makeTableModelList(geneticAlgorithm.getStatistics());
  }
  private List<TableModel> makeTableModelList(Statistics<Boolean> stats){
    int size = stats.getBestIndividuals().size();
    List<TableModel> tableModelList = new ArrayList<>();
    for (int i=0; i<size;i++){
      tableModelList.add(new TableModel(i,stats.getBestIndividuals().get(i),stats.getHistory().get(i)));
    }
    return tableModelList;
  }
}
