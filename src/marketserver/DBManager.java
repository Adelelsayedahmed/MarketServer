/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

/**
 *
 * @author tolan
 */
public class DBManager {
    
    public static UsrData getUser(String email, String password){
        UsrData user = new UsrData();
        // if email or password not found set UsrData.response to failed
        // else set response to success and fill UsrData attributes
        return user;
    } 
    
    public static Market getMarket(){
        Market market = new Market();
        // set market attributes using db values
        return market;
    }
    
    public static void setMarket(Market market){
        // update market values in db
    }
}
