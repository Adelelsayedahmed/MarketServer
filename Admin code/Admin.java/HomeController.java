package com.example.guiadmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public void  SwitchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterNewClientScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchToViewMarket(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MarketScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchToRemoveUser(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("UsersScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
