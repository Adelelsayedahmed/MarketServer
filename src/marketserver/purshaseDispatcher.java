/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

/**
 *
 * @author tolan
 */
public class purshaseDispatcher {
    public static void registerPurshase(Cart c){
        //update data in database
        MarketManager.updateMarket(c.itemQuantities);
    }
}