package com.example.guiclient;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static com.example.guiclient.StartController.c;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Registercontroller implements Initializable {

    @FXML TextField Firstname ;
    @FXML TextField Lastname ;
    @FXML TextField Email ;
    @FXML TextField password ;
    @FXML TextField Phone ;
    @FXML TextField Address ;


    @FXML
    public  void  SwitchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   public void register (ActionEvent event) throws IOException {
             if ( Constrains()  == true ) {
                 if (c.Create_Account(Firstname.getText(), Lastname.getText(), Email.getText(), password.getText(), Phone.getText(), Address.getText()) == true) {
                     Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       SetProperties();

    }
    public void SetProperties(){
        Phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    Phone.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    public boolean Constrains(){
        if (Firstname.getText().equals("")){
            itemshoppingcontroller.Alert("First name field can't be empty");
            return false ;
        }
        else if (Lastname.getText().equals("")){
            itemshoppingcontroller.Alert("Last name field can't be empty");
            return false ;
        }
        else if (Phone.getText().equals("")) {
            itemshoppingcontroller.Alert("Phone field can't be empty");
            return false ;
        }
        else if (Email.getText().equals("")) {
            itemshoppingcontroller.Alert("Email field can't be empty");
            return false ;
        }
        else if (Address.getText().equals("")) {
            itemshoppingcontroller.Alert("Address field can't be empty");
            return false ;
        }
        else if (password.getText().equals("")){
            itemshoppingcontroller.Alert("Password field can't be empty");
            return false ;
        }
        else return true ;

    }
}
