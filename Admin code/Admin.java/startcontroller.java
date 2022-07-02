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

public class startcontroller {
    static String IP = "" ;
    @FXML
    private TextField Temail ;
    @FXML
    private TextField Tpassword ;
    @FXML
            private TextField IPF ;
     Admin admin =new Admin();
    @FXML
    public  void  PressLogin(ActionEvent event) throws IOException {
        IP= IPF.getText() ;
        if(admin.Login(Temail.getText() ,Tpassword.getText())==true){
            Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error");
            errorAlert.setContentText("Invalid Email or Password");
            errorAlert.showAndWait();
        }
    }

}