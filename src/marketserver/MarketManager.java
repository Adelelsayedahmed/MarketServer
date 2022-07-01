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
    public static Market market;

    public static Market getMarketData(){
        market = DBManager.getMarket();
        return market;
    }
    
    // Get MarketData from database and set market   
    public static String updateMarket(Cart cart){
        for(int i = 0; i < 8; i++){
            if(cart.items[i].getStock() > getMarketData().getItems()[i].getStock()){
                return "failed";
            }
        }for(int i = 0; i < 8; i++){
            int stock = market.getItems()[i].getStock();
            market.getItems()[i].setStock(stock - cart.items[i].getStock());
        }
        DBManager.deposit(cart.email, -cart.totalCost);
        DBManager.setMarket(market);
        return "success";
    }
}
