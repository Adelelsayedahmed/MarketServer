package com.example.guiclient;

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

public class StartController {
    static Client c = new Client();
    static String IP="" ;
    @FXML
   private TextField Temail ;
    @FXML
   private TextField Tpassword ;
  @FXML
  private TextField IPf  ;
    @FXML
    public  void  PressLogin(ActionEvent event) throws IOException {
         IP= IPf.getText() ;
        if(c.Login(Temail.getText() ,Tpassword.getText())==true){
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
    public  void  SwitchToRegister(ActionEvent event) throws IOException {
        IP= IPf.getText() ;
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
