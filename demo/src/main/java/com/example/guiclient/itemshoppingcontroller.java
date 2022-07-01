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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static com.example.guiclient.StartController.c;

public class itemshoppingcontroller implements Initializable  {

    static ArrayList<String>ItemsName ;
    static ArrayList<Double>ItemsPrice;
    static ArrayList<Integer>ItemsStock;
    ArrayList<Integer>ItemsID;
   static ArrayList<Item> MarketItemObject = new ArrayList<>() ;
    @FXML Label I1Name , I2Name , I3Name , I4Name, I5Name , I6Name , I7Name , I8Name;
    @FXML TextField I1P, I1Q,I2P, I2Q ,I3P, I3Q,I4P, I4Q,I5P, I5Q,I6P, I6Q ,I7P, I7Q ,I8P, I8Q ,TBalance,TSearch;

    public static void MarketItemObject() {
    }


    @FXML
    public  void  SwitchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public  void  SwitchToCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CartScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c.getCartObeject().emptyCart();
        ItemsName = new ArrayList<>() ;
        ItemsPrice= new ArrayList<>() ;
        ItemsStock = new ArrayList<>() ;
        ItemsID    = new ArrayList<>() ;


        GetMarket();
        CreateItemsObject();
       // test();
        SettingProperties();

        SettingMarketlabelsNamesAndPrice();
    }
    public void GetMarket(){
        MarketItemObject.clear();
        ItemsStock.clear();
        ItemsName.clear();
        ItemsPrice.clear();

        try{
            Socket connection = new Socket(StartController.IP, 6969);

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject RequestMarket = new JSONObject();
                RequestMarket.put("requestType" , "market") ;
                out.writeObject(RequestMarket);
                JSONObject MarketResponse = (JSONObject) in.readObject();
                for (int i = 1 ; i<=8 ; i++){
                    ItemsName.add(MarketResponse.get("ItemName"+i)+"");
                }
                for (int i = 1 ; i<=8 ; i++){
                    ItemsPrice.add(Double.valueOf(MarketResponse.get("ItemPrice"+i)+""));
                }
                for (int i = 1 ; i<=8 ; i++){
                    ItemsStock.add((int)(MarketResponse.get("ItemQuantity"+i)));
                }


                System.out.println(MarketResponse.toString());
                break;
            }

            out.close();
            in.close();
            connection.close();

        }catch(Exception e){}

    }
    public void SettingMarketlabelsNamesAndPrice(){
        /* Setting Items Name*/
        I1Name.setText(MarketItemObject.get(0).getItemName());
        I2Name.setText(MarketItemObject.get(1).getItemName());
        I3Name.setText(MarketItemObject.get(2).getItemName());
        I4Name.setText(MarketItemObject.get(3).getItemName());
        I5Name.setText(MarketItemObject.get(4).getItemName());
        I6Name.setText(MarketItemObject.get(5).getItemName());
        I7Name.setText(MarketItemObject.get(6).getItemName());
        I8Name.setText(MarketItemObject.get(7).getItemName());
            /*Setting Items Price*/
        I1P.setText(String.valueOf(MarketItemObject.get(0).getPrice()));
        I2P.setText(String.valueOf(MarketItemObject.get(1).getPrice()));
        I3P.setText(String.valueOf(MarketItemObject.get(2).getPrice()));
        I4P.setText(String.valueOf(MarketItemObject.get(3).getPrice()));
        I5P.setText(String.valueOf(MarketItemObject.get(4).getPrice()));
        I6P.setText(String.valueOf(MarketItemObject.get(5).getPrice()));
        I7P.setText(String.valueOf(MarketItemObject.get(6).getPrice()));
        I8P.setText(String.valueOf(MarketItemObject.get(7).getPrice()));
    }

    public void CreateItemsObject(){
        MarketItemObject.clear();
        for (int i = 0 ; i < 8 ; i++ ){
            Item temp  = new Item(ItemsName.get(i), ItemsPrice.get(i) , ItemsStock.get(i) , 0 , 0) ;
             MarketItemObject.add(temp) ;
        }
    }


    public void SettingProperties (){
        I1P.setEditable(false);I2P.setEditable(false);I3P.setEditable(false);I4P.setEditable(false);I5P.setEditable(false);I6P.setEditable(false);I7P.setEditable(false);I8P.setEditable(false);;TBalance.setEditable(false);
        TBalance.setText(String.valueOf(c.getBalance()));
        I1Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I1Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I2Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I2Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I3Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I3Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I4Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I4Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I5Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I5Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I6Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I6Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I7Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I7Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        I8Q.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    I8Q.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }



 /*   public void test (){
        ItemsName.add("Mango")      ; ItemsPrice.add(23.0) ; ItemsStock.add(10)  ; ItemsID.add(1) ;
        ItemsName.add("Kaka")       ; ItemsPrice.add(12.5) ; ItemsStock.add(20)  ; ItemsID.add(2) ;
        ItemsName.add("Banana")     ; ItemsPrice.add(11.0) ; ItemsStock.add(30)  ; ItemsID.add(3) ;
        ItemsName.add("Strawberry") ; ItemsPrice.add(15.0) ; ItemsStock.add(40)  ; ItemsID.add(4) ;
        ItemsName.add("Tomatoes")   ; ItemsPrice.add(7.0)  ; ItemsStock.add(50) ; ItemsID.add(5) ;
        ItemsName.add("Watermelon") ; ItemsPrice.add(6.0)  ; ItemsStock.add(60) ; ItemsID.add(6) ;
        ItemsName.add("Potatoes")   ; ItemsPrice.add(9.0)  ; ItemsStock.add(70)  ; ItemsID.add(7) ;
        ItemsName.add("Peach")      ; ItemsPrice.add(8.0)  ; ItemsStock.add(80) ; ItemsID.add(8) ;

    }*/
    public void search (ActionEvent e ){
        if (!TSearch.getText().isEmpty()){
        String temp = TSearch.getText() ;
        int index = c.search(temp) ;
        if (index !=-1){
            AlertSearchControler.display("Search" ,"the item has been found", temp ,MarketItemObject.get(index).price, MarketItemObject.get(index)  );
        }
        else {Alert("Sorry item is not available");}
        }
        else {
            Alert("Enter The name of the item");
        }
    }
    public void handleButton0(){
        if (I1Q.getText().isEmpty()==false &&Integer.parseInt(I1Q.getText())!=0 ){
            int q =Integer.parseInt(I1Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(0),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(0), q);
                I1Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(0).stock-itemshoppingcontroller.MarketItemObject.get(0).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");
    }
    public void handleButton1(){
        if (I2Q.getText().isEmpty()==false && Integer.parseInt(I2Q.getText()) !=0){
            int q =Integer.parseInt(I2Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(1),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(1), q);
                I2Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(1).stock-itemshoppingcontroller.MarketItemObject.get(1).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");
    }
    public void handleButton2(){
        if (I3Q.getText().isEmpty()==false&&Integer.parseInt(I3Q.getText())!=0){
            int q =Integer.parseInt(I3Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(2),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(2), q);
                I3Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(2).stock-itemshoppingcontroller.MarketItemObject.get(2).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");
    }
    public void handleButton3(){
        if (I4Q.getText().isEmpty()==false && Integer.parseInt(I4Q.getText())!=0){
            int q =Integer.parseInt(I4Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(3),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(3), q);
                I4Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(3).stock-itemshoppingcontroller.MarketItemObject.get(3).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");
    }
    public void handleButton4(){
        if (I5Q.getText().isEmpty()==false && Integer.parseInt(I5Q.getText())!=0){
            int q =Integer.parseInt(I5Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(4),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(4), q);
                I5Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(4).stock-itemshoppingcontroller.MarketItemObject.get(4).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");
    }
    public void handleButton5(){
        if (I6Q.getText().isEmpty()==false&&Integer.parseInt(I6Q.getText())!=0){
            int q =Integer.parseInt(I6Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(5),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(5), q);
                I6Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(5).stock-itemshoppingcontroller.MarketItemObject.get(5).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");
    }
    public void handleButton6(){

        if (I7Q.getText().isEmpty()==false&&Integer.parseInt(I7Q.getText())!=0 ){
            int q =Integer.parseInt(I7Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(6),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(6), q);
                I7Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(6).stock-itemshoppingcontroller.MarketItemObject.get(6).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");


    }
    public void handleButton7(){
        if (I8Q.getText().isEmpty()==false||Integer.parseInt(I8Q.getText())==0){
            int q =Integer.parseInt(I8Q.getText());
            if (check(itemshoppingcontroller.MarketItemObject.get(7),q)){
                c.AddToCart(itemshoppingcontroller.MarketItemObject.get(7), q);
                I8Q.clear();}
            else {
                int reminder=  itemshoppingcontroller.MarketItemObject.get(7).stock-itemshoppingcontroller.MarketItemObject.get(7).bq ;
                Alert("Sorry we have only "+ reminder +" Kilo left");
            }
        }
        else Alert("Please Enter a correct quantity");
    }
    public boolean check(Item item , int q ){
        if( (item.bq+q) <= item.stock )
            return true ;
        else return false ;
    }
   public static void Alert(String s ){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error!");
            errorAlert.setContentText(s );
            errorAlert.showAndWait();

    }


}
