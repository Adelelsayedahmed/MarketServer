/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.util.Enumeration;
import java.util.Hashtable;
import java.io.*;
/**
 *
 * @author tolan
 */
public class Cart implements Serializable {
    Hashtable<String, Integer> itemQuantities;
    Hashtable<String, Integer> itemCosts;
    int totalCost;
    
    public Cart(Hashtable<String, Integer> q, Hashtable<String, Integer> c){
        this.itemQuantities = q;
        this.itemCosts = c;
        this.totalCost = calculateTotal();
    }

    public Cart(Hashtable<String, Integer> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }
    
    

    public Hashtable<String, Integer> getItemQuantities() {
        return itemQuantities;
    }

    public void setItemQuantities(Hashtable<String, Integer> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public Hashtable<String, Integer> getItemCosts() {
        return itemCosts;
    }

    public void setItemCosts(Hashtable<String, Integer> itemCosts) {
        this.itemCosts = itemCosts;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int calculateTotal() {
        int cost = 0;
        Enumeration<String> items = itemCosts.keys();
        while (items.hasMoreElements()) {
            String key = items.nextElement();
            cost = cost + itemCosts.get(key);
        }
        return cost;
    }
    
    
}
