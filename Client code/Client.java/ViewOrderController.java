package com.example.guiclient;

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
import javafx.scene.control.cell.PropertyValueFactory;
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

public class ViewOrderController implements Initializable{
    @FXML
    private TableView<Orders> OrdersTable ;
    @FXML
    private TableColumn<Orders, String> dateCol;
    @FXML
    private  TableColumn<Orders,Double> priceCol;

    ArrayList<Orders> OrdersObjects = new ArrayList<>() ;
     ArrayList<String> OrderDate ;
     ArrayList<Double> OrderPrice ;


    ObservableList<Orders> rOrders ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // OrdersObjects.clear();
        OrderDate = new ArrayList<>() ;
        OrderPrice= new ArrayList<>() ;

        dateCol.setCellValueFactory(new PropertyValueFactory<Orders,String>("Date"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Orders ,Double>("Price"));

       CallServer();
        CreateObjects();
        SetTableItem();

    }
    public void SetTableItem(){
     rOrders = OrdersTable.getItems();
     rOrders.addAll(OrdersObjects);
     OrdersTable.setItems(rOrders);
    }
    public void CallServer(){
       String myEmail = c.getEmail() ;

        try{
            Socket connection = new Socket(StartController.IP, 6969);
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject RequestMarket = new JSONObject();
                RequestMarket.put("requestType" , "getorders") ;
                RequestMarket.put("email",myEmail) ;
                System.out.println(RequestMarket.toString());
                out.writeObject(RequestMarket);
                JSONObject MarketResponse = (JSONObject) in.readObject();

                System.out.println(MarketResponse.toString());
                int n = Integer.parseInt((MarketResponse.get("no")+""));
                System.out.println(n);
                for (int i = 1 ; i<=n ; i++){
                    OrderDate.add((MarketResponse.get("date"+i)+""));
                }
                for (int i = 1 ; i<=n ; i++){
                    OrderPrice.add(Double.valueOf(MarketResponse.get("cost"+i)+""));
                }


                System.out.println(MarketResponse.toString());
                break;
            }

            out.close();
            in.close();
            connection.close();

        }catch(Exception e){
            System.out.println("A7a");
        }





    }
    public void CreateObjects(){

    for (int i = 0 ; i < OrderDate.size() ; i++)
    {
        OrdersObjects.add(new Orders(OrderDate.get(i) , OrderPrice.get(i)))  ;
    }
    }
    public void Back(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
