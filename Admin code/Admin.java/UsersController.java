package com.example.guiadmin;

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

public class UsersController  implements Initializable {
    @FXML
    private TableView<client> ClientTable;

    @FXML
    private TableColumn<client, String> fnCol;
    @FXML
    private TableColumn<client, String> LnCol;
    @FXML
    private TableColumn<client, String> EmailCol;
    @FXML
    private TableColumn<client, Double> BalanceCol;
    @FXML
    private TableColumn<client, String> PhoneCol;
     ObservableList<client> rClient;
    ArrayList<client> Client = new ArrayList<>() ;
    ArrayList<String> ClientFn ;
    ArrayList<String> ClientLn ;
    ArrayList<String> ClientEmail ;
    ArrayList<Double> ClientBalance ;
    ArrayList<String> ClientPhoneNumber ;



    @FXML
    public void SwitchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fnCol.setCellValueFactory(new PropertyValueFactory<client, String>("First_Name"));
        BalanceCol.setCellValueFactory(new PropertyValueFactory<client, Double>("balance"));
        LnCol.setCellValueFactory(new PropertyValueFactory<client, String>("Last_Name"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<client, String>("Email"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<client, String>("Phone"));

    ClientFn = new ArrayList<>();
    ClientLn = new ArrayList<>() ;
    ClientBalance = new ArrayList<>() ;
    ClientPhoneNumber = new ArrayList<>() ;
    ClientEmail = new ArrayList<>();
    CallingServer();
    CreateClientObject();
    SetTable() ;
    }
    public void CreateClientObject(){

        for (int i = 0 ; i < ClientEmail.size() ; i++){
            Client.add( new client(  ClientFn.get(i) , ClientLn.get(i) , ClientEmail.get(i) , ClientPhoneNumber.get(i) , ClientBalance.get(i)   ) ) ;
        }

    }
    public void CallingServer() {

        int size = 0 ;


        try{
            Socket connection = new Socket(startcontroller.IP, 6969);

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject RequestMarket = new JSONObject();
                RequestMarket.put("requestType" , "getusers") ;
                out.writeObject(RequestMarket);
                JSONObject MarketResponse = (JSONObject) in.readObject();
                size = Integer.parseInt(MarketResponse.get("no")+"");

                for (int i = 1 ; i<=size ; i++){
                    ClientEmail.add(MarketResponse.get("email"+i)+"");
                    ClientFn.add(MarketResponse.get("firstName"+i)+"") ;
                    ClientLn.add(MarketResponse.get("lastName"+i)+"") ;
                    ClientPhoneNumber.add(MarketResponse.get("phoneno"+i)+"");
                    ClientBalance.add(Double.valueOf(MarketResponse.get("balance"+i)+"")) ;
                }


                System.out.println(MarketResponse.toString());
                break;
            }

            out.close();
            in.close();
            connection.close();

        }catch(Exception e){}










    }
    public void SetTable() {
        rClient = ClientTable.getItems();
        rClient.addAll(Client);
        ClientTable.setItems(rClient);
    }

    @FXML
    public void delete(){
        String nameToUpdate = ClientTable.getSelectionModel().getSelectedItem().getEmail();
        ClientTable.getItems().removeAll(ClientTable.getSelectionModel().getSelectedItem());


        try{
            Socket connection = new Socket(startcontroller.IP, 6969);

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject RequestMarket = new JSONObject();
                RequestMarket.put("requestType" , "deleteuser") ;
                RequestMarket.put("email",nameToUpdate);
                out.writeObject(RequestMarket);

                break;
            }

            out.close();
            in.close();
            connection.close();

        }catch(Exception e){}



    }
}
