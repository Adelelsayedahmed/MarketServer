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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.guiclient.StartController.c;

public class profilescreencontroller implements Initializable {

    @FXML
    private TextField name ;
    @FXML
    private TextField address ;
    @FXML
    private TextField  account ;
    @FXML
    private TextField balance ;
    @FXML
    private TextField phone ;
    @FXML
    private TextField DepoistAmount;
    @FXML
    public  void  SwitchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public  void  SwitchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void deposit(ActionEvent event) throws IOException {
        if (DepoistAmount.getText().isEmpty() == false && Double.parseDouble(DepoistAmount.getText()) >0) {
            c.deposit(Double.parseDouble(DepoistAmount.getText()));
            balance.setText(String.valueOf(c.getBalance()));
            DepoistAmount.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your balance has been updated");
            alert.setContentText("Your new balance became " + c.getBalance());
            alert.showAndWait() ;
        }
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error!");
            errorAlert.setContentText("Please Enter a correct value" );
            errorAlert.showAndWait();
            DepoistAmount.clear();

        }
    }
    @Override
    public void initialize(URL url , ResourceBundle resourceBundle){
    name.setText(c.getFirst_Name() +" " +c.getLast_Name() );
    address.setText(c.getAddress());
    account.setText(c.getEmail());
    balance.setText( String.valueOf(c.getBalance()));
    phone.setText(c.getPhone());
    setProperties();

    }
    void setProperties(){
         name.setEditable(false);
         address.setEditable(false);
         account.setEditable(false);
         balance.setEditable(false);
         phone.setEditable(false);
         DepoistAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    DepoistAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
