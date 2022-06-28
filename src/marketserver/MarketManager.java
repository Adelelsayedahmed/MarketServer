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
    
    // Get market data from database
    
    public static Market getMarket() {
        return market;
    }

    public static void setMarket(Market m) {
        market = m;
    }
    
    public void getMarketData(){
        //gets data from database
    }
    
    // Get MarketData from database and set market   
    public static void updateMarket(Hashtable<String, Integer> marketUpdates){
        Enumeration<String> updatedkeys = marketUpdates.keys();
        while (updatedkeys.hasMoreElements()) {
            String key = updatedkeys.nextElement();
            market.marketItems.replace(key, market.marketItems.get(key) - marketUpdates.get(key));
        }
        //SessionDispatcher.sendSessionUpdate(market);
    }
    
    
}
