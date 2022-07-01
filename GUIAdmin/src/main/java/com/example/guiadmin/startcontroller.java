package com.example.guiadmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class startcontroller {
    @FXML
    private TextField Temail ;
    @FXML
    private TextField Tpassword ;
     Admin admin =new Admin();
    @FXML
    public  void  PressLogin(ActionEvent event) throws IOException {
        if(admin.Login(Temail.getText() ,Tpassword.getText())==true){
            Parent root = FXMLLoader.load(getClass().getResource("MarketScreen.fxml"));
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
    @FXML
    public  void  SwitchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterAdminScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}