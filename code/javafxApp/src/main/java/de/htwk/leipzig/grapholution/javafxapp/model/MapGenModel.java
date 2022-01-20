package de.htwk.leipzig.grapholution.javafxapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ModelKlasse GenerischeModels
 */

public class MapGenModel {

    /**
     * Liste von HillModels
     */
    List<MapHillModel> listMapHillModel = new ArrayList<>();

    /**
     *
     * @param listMapHillModel
     */
    public MapGenModel(List<MapHillModel> listMapHillModel) {
        this.listMapHillModel = listMapHillModel;
    }

}
