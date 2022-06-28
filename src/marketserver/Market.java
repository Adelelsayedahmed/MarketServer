/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.util.Hashtable;

/**
 *
 * @author tolan
 */
public class Market {
    public Hashtable<String, Integer> marketItems;
    public Hashtable<String, Integer> itemsPrices;

    public Market(Hashtable<String, Integer> marketItems, Hashtable<String, Integer> itemsPrices) {
        this.marketItems = marketItems;
        this.itemsPrices = itemsPrices;
    }
    
}
