package de.htwk.leipzig.grapholution.javafxapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ModelKlasse GenerischeModels
 */

public class GenModel {

    /**
     * Liste von HillModels
     */
    List<HillModel> listHillModel = new ArrayList<>();

    /**
     *
     * @param listHillModel
     */
    public GenModel(List<HillModel> listHillModel) {
        this.listHillModel = listHillModel;
    }

}
