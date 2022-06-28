/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marketserver;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author tolan
 */
public class MarketServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Socket client = null;
        try{
            Hashtable<String, Integer> itemQuantities = new Hashtable<String, Integer>();
            itemQuantities.put("Apples", 100);
            itemQuantities.put("Eggs", 100);
            itemQuantities.put("Potatoes", 100);
            itemQuantities.put("KAKA", 100);
            
            Hashtable<String, Integer> itemPrices = new Hashtable<String, Integer>();
            itemPrices.put("Apples", 20);
            itemPrices.put("Eggs", 10);
            itemPrices.put("Potatoes", 5);
            itemPrices.put("KAKA", 1000000);
            
            Market market = new Market(itemQuantities, itemPrices);
            MarketManager.setMarket(market);
            
            ServerSocket server = new ServerSocket(6969);
            client = server.accept();
            ObjectOutputStream response = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream cartInfo = new ObjectInputStream(client.getInputStream());
            Cart cart = (Cart) cartInfo.readObject();
            purshaseDispatcher.registerPurshase(cart);
            
            Enumeration<String> marketItems = MarketManager.getMarket().marketItems.keys();
            while (marketItems.hasMoreElements()) {
                String key = marketItems.nextElement();
                System.out.println(key + MarketManager.getMarket().marketItems.get(key));
            }

            response.close();
            cartInfo.close();
            client.close();
            server.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        
    }
    
}
