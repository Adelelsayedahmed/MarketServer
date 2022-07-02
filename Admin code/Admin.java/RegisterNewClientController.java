package com.example.guiadmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RegisterNewClientController {

    @FXML
    TextField Firstname ;
    @FXML TextField Lastname ;
    @FXML TextField Email ;
    @FXML TextField password ;
    @FXML TextField Phone ;
    @FXML TextField Address ;
    Admin admin = new Admin () ;

     @FXML
    public void register (ActionEvent event) throws IOException {
        if ( Constrains()  == true ) {
            if (admin.Create_Account(Firstname.getText(), Lastname.getText(), Email.getText(), password.getText(), Phone.getText(), Address.getText()) == true) {
                Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                errorAlert.setHeaderText("Client Registration");
                errorAlert.setContentText("Client Account is created successfly");
                errorAlert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("error");
                errorAlert.setContentText("this email is already registered");
                errorAlert.showAndWait();
            }
        }

    }
    public boolean Constrains(){
        if (Firstname.getText().equals("")){
            Alert("First name field can't be empty");
            return false ;
        }
        else if (Lastname.getText().equals("")){
            Alert("Last name field can't be empty");
            return false ;
        }
        else if (Phone.getText().equals("")) {
            Alert("Phone field can't be empty");
            return false ;
        }
        else if (Email.getText().equals("")) {
            Alert("Email field can't be empty");
            return false ;
        }
        else if (Address.getText().equals("")) {
            Alert("Address field can't be empty");
            return false ;
        }
        else if (password.getText().equals("")){
            Alert("Password field can't be empty");
            return false ;
        }
        else return true ;

    }
    @FXML
    public void SwitchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public static void Alert(String s ){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Error!");
        errorAlert.setContentText(s );
        errorAlert.showAndWait();

    }


}
