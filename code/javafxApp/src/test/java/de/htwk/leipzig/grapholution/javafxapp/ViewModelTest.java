package de.htwk.leipzig.grapholution.javafxapp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ViewModelTest {

    private final StringProperty inputField = new SimpleStringProperty("testInput");
    private final StringProperty outputField= new SimpleStringProperty("testOutput");
    private final ViewModel viewModel = new ViewModel();

    @Test
    void InputTextFieldTest_returnsString(){

    }

    @Test
    void onbuttonClickTest(){


    }
}
