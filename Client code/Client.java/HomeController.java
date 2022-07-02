package com.example.guiclient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.event.ActionEvent ;
public class HomeController   {


    @FXML
    public  void  SwitchToProfileScene(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("ProfileScreen.fxml"));
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
    public  void  SwitchItemShoppingScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("itemshopping.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public  void  SwitchToOrders(ActionEvent event) throws IOException {
       try{ Parent root = FXMLLoader.load(getClass().getResource("ViewOrderScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

       catch (Exception e){
           System.out.println("x");
           Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
           Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();





       }
    }



    }
