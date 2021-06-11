package modelViewViewModel;

import javafx.beans.property.*;

public class LabeledTextFieldViewModel implements ViewModel {
    private StringProperty labelText = new SimpleStringProperty("default");

    private BooleanProperty buttonDisabled = new SimpleBooleanProperty();

    private StringProperty inputText = new SimpleStringProperty("");

    public LabeledTextFieldViewModel() {
        buttonDisabled.bind(inputText.isEmpty());
    }

    public void changeLabel() {
        labelText.set(inputText.get());
        inputText.set("");
    }

    public ReadOnlyStringProperty labelTextProperty() {
        return labelText;
    }

    public ReadOnlyBooleanProperty buttonDisabledProperty() {
        return buttonDisabled;
    }

    public StringProperty inputTextProperty() {
        return inputText;
    }

    @Override
    public void unsubscribe(int identityHashCode, String messageName, Object o) {

    }
}