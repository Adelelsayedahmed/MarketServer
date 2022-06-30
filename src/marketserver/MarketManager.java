/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

import java.util.Hashtable;
import java.util.Enumeration;

/**
 *
 * @author tolan
 */
public class MarketManager {
    private static Market market;
    
    public static Market getMarket() {
        return market;
    }
    
    public static Market getMarketData(){
        return DBManager.getMarket();
    }
    
    // Get MarketData from database and set market   
    public static String updateMarket(Cart cart){
        for(int i = 0; i <= 10; i++){
            if(cart.items[i].getStock() > market.getItems()[i].getStock()){
                return "failed";
            }
        }for(int i = 0; i <= 10; i++){
            int stock = market.getItems()[i].getStock();
            market.getItems()[i].setStock(stock - cart.items[0].getStock());
        }
        DBManager.setMarket(market);
        return "success";
    }
}
