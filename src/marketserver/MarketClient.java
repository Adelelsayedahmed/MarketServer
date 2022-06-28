/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marketserver;
import java.net.*;
import java.io.*;
import java.util.Hashtable;

/**
 *
 * @author tolan
 */
public class MarketClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Socket client = new Socket("localhost", 6969);
            ObjectInputStream response = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream cartInfo = new ObjectOutputStream(client.getOutputStream());
            Hashtable<String, Integer> itemQuantities = new Hashtable<String, Integer>();
            itemQuantities.put("Apples", 5);
            itemQuantities.put("Eggs", 10);
            itemQuantities.put("Potatoes", 2);
            itemQuantities.put("KAKA", 100);
            Cart cart = new Cart(itemQuantities);
            cartInfo.writeObject(cart);
            cartInfo.flush();
            cartInfo.close();
            response.close();
            client.close();
            
            
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
}
