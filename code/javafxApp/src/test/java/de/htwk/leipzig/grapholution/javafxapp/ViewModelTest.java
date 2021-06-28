package de.htwk.leipzig.grapholution.javafxapp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import junit.framework.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ViewModelTest {

    private StringProperty inputField;
    private StringProperty outputField;

    @Test
    public void setup(){
        inputField = new SimpleStringProperty();
        outputField= new SimpleStringProperty();

        
    }
    @Test
    public void testInputTextField(){

    }

    @Test
    public void onbuttonClickTest(){


    }
}
