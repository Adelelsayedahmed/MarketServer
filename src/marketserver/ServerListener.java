/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import org.json.simple.JSONObject;
/**
 *
 * @author tolan
 */
public class ServerListener {
    private static ServerSocket server;
    
    public static void start(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("Server is up on port "+port+".");
            System.out.println("Ready to recieve client requests.");
            while(true){
                new ClientHandler(server.accept()).start();
            }
        }catch(Exception e){}
    }
    public void stop(){
        try{
        server.close();
        }catch(Exception e){}
    }
    
    private static class ClientHandler extends Thread{
        private Socket client;
        private ObjectInputStream in;
        private ObjectOutputStream out;
    
        public ClientHandler(Socket socket){
            this.client = socket;
        }
    
        public void run(){
            // recieve requests from clients 
            try{
                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream(client.getInputStream());
                while(true){
                    JSONObject req = (JSONObject) in.readObject();
                    String requestType = (String) req.get("requestType");
                    System.out.println(requestType);
                    System.out.println(req.toJSONString());
                    if(requestType.equals("login")){
                        Login login = JSON.parseLogin(req);
                        SessionInfo response = login.login();
                        out.writeObject(JSON.jsonifySessionInfo(response));
                    }else if(requestType.equals("market")){
                        JSONObject market = JSON.jsonifyMarket(MarketManager.getMarketData());
                        System.out.println(market.toString());
                        out.writeObject(market);
                    }else if(requestType.equals("cart")){
                        Cart cart = JSON.parseCart(req);
                        String res = MarketManager.updateMarket(cart);
                        if(res.equals("success")){
                            JSONObject response = JSON.jsonifyMarket(MarketManager.market); 
                            response.put("response", "success");
                            out.writeObject(response);
                        }else{
                            JSONObject response = JSON.jsonifyMarket(MarketManager.getMarketData());
                            response.put("response", "failed");
                            out.writeObject(response);
                        }
                    }else if(requestType.equals("deposit")){
                        Deposit deposit = JSON.parseDeposit(req);
                        Wallet.deposit(deposit.email, deposit.amount);
                    }else if(requestType.equals("register")){
                        Register register = JSON.parseRegister(req);
                        DBManager.addUser(register);
                        JSONObject response = new JSONObject();
                        out.writeObject(response);
                    }else if(requestType.equals("editprices")){
                        Edit edit = JSON.parseEditPrices(req);
                        DBManager.editPrices(edit);
                    }else if(requestType.equals("editstock")){
                        Cart editedStock = JSON.parseCart(req);
                        Market market = new Market(editedStock.items);
                        DBManager.editStock(market);
                    }else if(requestType.equals("getusers")){
                        ArrayList<UsrData> users = DBManager.getUsers();
                        JSONObject usersData = JSON.jsonifyUsers(users);
                        out.writeObject(usersData);
                    }else if(requestType.equals("getorders")){
                        ArrayList<Order> orders = DBManager.getOrders((String) req.get("email"));
                        JSONObject ordersData = JSON.jsonifyOrders(orders);
                        out.writeObject(ordersData);
                    }else if(requestType.equals("deleteuser")){
                        DBManager.deleteUser((String) req.get("email"));
                    }
                    in.close();
                    out.close();
                    client.close();
                }
            }catch(Exception e){}
    }
}
}
