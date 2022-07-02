package com.example.guiadmin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.json.simple.JSONObject;
public class MakretController implements Initializable {
    @FXML
    private TextField newP;
    @FXML
    private TextField newQ;
    @FXML
    private TableView<Item> ItemTable;

    @FXML
    private TableColumn<Item, String> NameCol;
    @FXML
    private TableColumn<Item, Double> PriceCol;
    @FXML
    private TableColumn<Item, Integer> StockCol;
    ArrayList<Item> MarketItemObject = new ArrayList<>();

    ArrayList<String> ItemsName;
    ArrayList<Double> ItemsPrice;
    ArrayList<Integer> ItemsStock;
     ObservableList<Item> rItem;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        ItemsName = new ArrayList<>();
        ItemsPrice = new ArrayList<>();
        ItemsStock = new ArrayList<>();
         //GetMaket;
        Server();
        CreateItemsObject();
        NameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("ItemName"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
        StockCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("stock"));
        setItems();
        SetProperties();

    }

    public void CreateItemsObject() {
        for (int i = 0; i < 8; i++) {
            Item temp = new Item(ItemsName.get(i), ItemsPrice.get(i), ItemsStock.get(i));
            MarketItemObject.add(temp);
        }
    }

    public void setItems() {
        rItem = ItemTable.getItems();
        rItem.addAll(MarketItemObject);
        ItemTable.setItems(rItem);
    }

    public void Server(){

        try{
            Socket connection = new Socket(startcontroller.IP, 6969);

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
    public void UpdatePrice(ActionEvent event) throws IOException {
        if (!newP.getText().equals("")){

        String nameToUpdate = ItemTable.getSelectionModel().getSelectedItem().getItemName();
        for (int i = 0; i < MarketItemObject.size(); i++) {
            if (MarketItemObject.get(i).getItemName().equals(nameToUpdate)) {
                MarketItemObject.get(i).setPrice(Double.parseDouble(newP.getText()));
                ItemTable.refresh();
            }
        }
        /*Call server*/
            try {
                Socket connection = new Socket(startcontroller.IP, 6969);
                ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

                while (true) {
                    JSONObject CheckCart = new JSONObject();
                    CheckCart.put("requestType", "editprices");

                    for (int i = 1; i <= 8; i++) {
                        CheckCart.put(i + "", MarketItemObject.get(i - 1).getPrice());
                    }

                    out.writeObject(CheckCart);
                    out.close();
                    in.close();
                    connection.close();
                    break;

                }
            }
                catch(Exception e){
                        return ;
                    }



    }
        else RegisterNewClientController.Alert("price field can't be empty");
        newP.clear();

    }


    public void UpdateQuantity(ActionEvent event) throws IOException {
        try{
            if (!newQ.getText().equals("")) {
                String nameToUpdate = ItemTable.getSelectionModel().getSelectedItem().getItemName();
                for (int i = 0; i < MarketItemObject.size(); i++) {
                    if (MarketItemObject.get(i).getItemName().equals(nameToUpdate)) {
                        MarketItemObject.get(i).setStock(Integer.parseInt(newQ.getText()));

                        ItemTable.refresh();

                    }

                }


                /*Server Call*/
                try {
                    Socket connection = new Socket(startcontroller.IP, 6969);
                    ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

                    while (true) {
                        JSONObject CheckCart = new JSONObject();
                        CheckCart.put("requestType", "editstock");

                        for (int i = 1; i <= 8; i++) {
                            CheckCart.put(i + "", MarketItemObject.get(i - 1).getStock());
                        }

                        out.writeObject(CheckCart);
                        out.close();
                        in.close();
                        connection.close();
                        break;

                    }
                }
                catch(Exception e){
                    return ;
                }

            }

            else RegisterNewClientController.Alert("Quantity field can't be empty");
            newQ.clear();}
        catch (Exception e){
            RegisterNewClientController.Alert("Select a valid row");
        }
    }

    public void test (){
      ItemsName.add("Mango")      ; ItemsPrice.add(23.0) ; ItemsStock.add(10)   ;
      ItemsName.add("Kaka")       ; ItemsPrice.add(12.5) ; ItemsStock.add(20)  ;
      ItemsName.add("Banana")     ; ItemsPrice.add(11.0) ; ItemsStock.add(30)   ;
      ItemsName.add("Strawberry") ; ItemsPrice.add(15.0) ; ItemsStock.add(40)  ;
      ItemsName.add("Tomatoes")   ; ItemsPrice.add(7.0)  ; ItemsStock.add(50)  ;
      ItemsName.add("Watermelon") ; ItemsPrice.add(6.0)  ; ItemsStock.add(60)  ;
      ItemsName.add("Potatoes")   ; ItemsPrice.add(9.0)  ; ItemsStock.add(70)   ;
      ItemsName.add("Peach")      ; ItemsPrice.add(8.0)  ; ItemsStock.add(80)   ;

  }

    @FXML
    public void Refresh(ActionEvent event)throws IOException{
                ItemsName.clear();
                ItemsPrice.clear();
                ItemsStock.clear();
                MarketItemObject.clear() ;

                Server();
                 CreateItemsObject();
               rItem.clear();
               setItems();
    }

    public void GetMarket(){
        try{

            Socket connection = new Socket("172.20.10.11", 6969);

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
     public void SetProperties(){
      newP.textProperty().addListener(new ChangeListener<String>() {
          @Override
          public void changed(ObservableValue<? extends String> observable, String oldValue,
                              String newValue) {
              if (!newValue.matches("\\d*")) {
                  newP.setText(newValue.replaceAll("[^\\d]", ""));
              }
          }
      });
      newQ.textProperty().addListener(new ChangeListener<String>() {
          @Override
          public void changed(ObservableValue<? extends String> observable, String oldValue,
                              String newValue) {
              if (!newValue.matches("\\d*")) {
                  newQ.setText(newValue.replaceAll("[^\\d]", ""));
              }
          }
      });
  }
    @FXML
    public void SwitchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
