package com.example.guiclient;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static com.example.guiclient.StartController.c;

public class AlertSearchControler {
    public static void display(String title , String message , String IName , Double Iprice , Item item  ){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label () ;
        TextField textField2 = new TextField() ;
        TextField TextField3 = new TextField() ;
        textField2.setText(Iprice+"");
        textField2.setEditable(false);
        TextField3.setPromptText("0");
        label.setText(message);
        TextField3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    TextField3.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        label.setMinWidth(50);
        label.setMinHeight(50) ;

        Button CloseButton = new Button("Close") ;
        Button AddToCart = new Button("Add to cart") ;

        AddToCart.setOnAction(e-> {
                    if (!TextField3.getText().isEmpty()) {
                        if (Integer.parseInt(TextField3.getText()) !=0){
                        if (item.getBq()+Integer.parseInt(TextField3.getText()) <=item.stock){
                        c.AddToCart(item, Integer.parseInt(TextField3.getText()));
                        window.close(); }
                        else {itemshoppingcontroller.Alert("Sorry we have only " +(item.stock- item.bq) +" Kilo left" );}
                    }
                     else  itemshoppingcontroller.Alert("Enter a non zero quantity"); ;
                    }
                    else itemshoppingcontroller.Alert("Please Enter a correct quantity ");
        });
        CloseButton.setOnAction(e -> window.close());

        VBox Layout = new VBox(50) ;
        Layout.getChildren().addAll(label , CloseButton,AddToCart, textField2 ,TextField3);
        Scene scene = new Scene(Layout) ;
        window.setScene(scene);
        window.showAndWait();
    }
}
