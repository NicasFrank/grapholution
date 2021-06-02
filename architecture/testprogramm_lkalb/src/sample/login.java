package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class login {

    public login() {

    }
    @FXML
    private Button login;
    @FXML
    private Label warning;
    @FXML
    private TextField benutzername;
    @FXML
    private PasswordField passwort;


    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        if(benutzername.getText().toString().equals("test") && passwort.getText().toString().equals("test")) {
            warning.setText("Jawohl, so muss dat!");

            m.changeScene("afterLogin.fxml");
        }

        else if(benutzername.getText().isEmpty() && passwort.getText().isEmpty()) {
            warning.setText("Los! Gib deine Daten ein!");
        }


        else {
            warning.setText("Benutzername oder Passwort stimmen nicht!");
        }
    }




    }
