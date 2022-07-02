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
        market = getMarketData();
        double cost = 0;
        for(int i = 0; i < 8; i++){
            if(cart.items[i].getStock() > market.getItems()[i].getStock()){
                return "failed";
            }
        }for(int i = 0; i < 8; i++){
            int stock = market.getItems()[i].getStock();
            market.getItems()[i].setStock(stock - cart.items[i].getStock());
            cost += market.getItems()[i].getPrice() * cart.items[i].getStock();
        }
        DBManager.deposit(cart.email, -cost);
        DBManager.editStock(market);
        DBManager.addOrder(cart.email, cart.items, cost);
        return "success";
    }
    
    public static void editStock(Cart cart){
        market = getMarketData();
        for(int i = 0; i < 8; i++){
            market.getItems()[i].setStock(cart.items[i].getStock());
        }
        DBManager.editStock(market);
    }
}
