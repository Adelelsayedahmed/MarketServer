package com.example.guiclient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;
import static com.example.guiclient.StartController.c;


public class Cartcontroller implements Initializable {

    @FXML
    TextField TA  ;
    @FXML TextField NQ ;
    @FXML
    private  TableView<Item> ItemTable ;

    @FXML
    private TableColumn<Item, String> nameCol;
    @FXML
    private  TableColumn<Item,Double> priceCol;
    @FXML
    private  TableColumn<Item,Integer> quanCol;


     static ObservableList<Item> rItem ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TA.setText(String.valueOf(c.getCartObeject().CalculateTotalCartPrice()));

        nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("ItemName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Item ,Double>("price"));
        quanCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("bq"));
        setItems();
        SetProperties();


    }
    public  void setItems(){
        rItem = ItemTable.getItems();
        rItem.addAll(c.CartObeject.CartItems);
        ItemTable.setItems(rItem);
    }
    public void cancel(ActionEvent event) throws   IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Order Cancellation");
        alert.setContentText("Do you want to cancel your Order ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()==ButtonType.OK)
            SwitchItemShoppingScene(event);
        else if (result.get()==ButtonType.CANCEL){}
    }
    public  void  SwitchItemShoppingScene(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("itemshopping.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

    public void Purchase (ActionEvent event) throws IOException {
        if (c.CartObeject.CartItems.isEmpty()==false){
        String response = c.purchase() ;
       if ( response.equals( "true" ) ){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("Order Confirmation");
           alert.setContentText("Your order will be delivered in 2 hours  ");
           alert.showAndWait();
               c.balance=c.balance-c.getCartObeject().CalculateTotalCartPrice() ;
               c.getCartObeject().emptyCart();
                                /*Call The database to update balance and item information*/
               SwitchItemShoppingScene(event);


       }
      else if (response=="insufficient"){
           Alert errorAlert = new Alert(Alert.AlertType.ERROR);
           errorAlert.setHeaderText("Error");
           errorAlert.setContentText("You don't have insufficient balance ");
           errorAlert.showAndWait();
       }
      else if (response=="not available"){
           Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
           errorAlert.setHeaderText("Order items not available");
           errorAlert.setContentText("We are sorry your items became not available");
           errorAlert.showAndWait();

      }
    }
        else {
    itemshoppingcontroller.Alert("Please fill your Cart first") ;
        SwitchItemShoppingScene(event);
        }
    }

    public void SwitchToEdit_Add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Edit_AddItemToCart.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void RemoveItemFromCart(ActionEvent event) throws IOException {

        try {
            String nametoremove = ItemTable.getSelectionModel().getSelectedItem().ItemName;
            for (int i = 0; i < c.CartObeject.CartItems.size(); i++) {
                if (c.CartObeject.CartItems.get(i).ItemName.equals(nametoremove)) {
                    c.CartObeject.CartItems.get(i).bq = 0;
                    c.CartObeject.CartItems.remove(i);

                    TA.setText(c.CartObeject.CalculateTotalCartPrice() + "");
                    break;
                }

            }
            ItemTable.getItems().removeAll(ItemTable.getSelectionModel().getSelectedItem());
        }

    catch (Exception e){

        itemshoppingcontroller.Alert("Cart is Already empty");
    }
    }
    public void UpdateQuantity(ActionEvent event) throws IOException {
        try{
        int newQuantity = Integer.parseInt(NQ.getText());
        if (newQuantity==0)
            RemoveItemFromCart(event);
        else {

            String nametoedit = ItemTable.getSelectionModel().getSelectedItem().ItemName;
            for (int i = 0; i < c.CartObeject.CartItems.size(); i++) {
                if (c.CartObeject.CartItems.get(i).ItemName.equals(nametoedit)) {

                   if ( checkQuantity(ItemTable.getSelectionModel().getSelectedItem(),newQuantity) ==true ) {
                       c.CartObeject.CartItems.get(i).bq = newQuantity;
                       TA.setText(c.CartObeject.CalculateTotalCartPrice() + "");
                       ItemTable.refresh();

                   }
                    else itemshoppingcontroller.Alert("Sorry we have only " + (c.CartObeject.CartItems.get(i).stock-c.CartObeject.CartItems.get(i).bq) + " left");

                    break;
                }
            }
        }
        }
        catch (Exception e){
            itemshoppingcontroller.Alert("Select a valid row");
        }
    }
    public boolean checkQuantity( Item item ,int nq  ){
        if (item.stock<nq )
            return false ;
        else return true ;
    }

    public void SetProperties(){
        TA.setEditable(false);
        NQ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    NQ.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

}

