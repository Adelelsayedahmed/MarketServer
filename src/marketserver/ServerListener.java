/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.io.*;
import java.net.*;
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
                
                JSONObject req = (JSONObject) in.readObject();
                String requestType = (String) req.get("requestType");
                while((req = (JSONObject) in.readObject()) != null){
                    if(requestType.equals("login")){
                        Login login = JSON.parseLogin(req);
                        SessionInfo response = login.login();
                        out.writeObject(JSON.jsonifySessionInfo(response));
                        out.flush();
                    }else if(requestType.equals("cart")){
                        Cart cart = JSON.parseCart(req);
                        if(MarketManager.updateMarket(cart) == "success"){
                            JSONObject response = JSON.jsonifyMarket(MarketManager.getMarket());
                            response.put("response", "success");
                            out.writeObject(response);
                            out.flush();
                        }else{
                            JSONObject response = JSON.jsonifyMarket(MarketManager.getMarket());
                            response.put("response", "failed");
                            out.writeObject(response);
                            out.flush();
                        }
                    }else if(requestType.equals("deposit")){
                        Deposit deposit = JSON.parseDeposit(req);
                        Wallet.deposit(deposit.email, deposit.amount);
                    }else if(requestType.equals("logout")){
                        in.close();
                        out.close();
                        client.close();
                        break;
                    }
                }       
            }catch(Exception e){}
        }
    }
}
