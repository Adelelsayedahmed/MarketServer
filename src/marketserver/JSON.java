/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import org.json.simple.JSONObject;
/**
 *
 * @author tolan
 */
public class JSON {
    public static JSONObject jsonifySessionInfo(SessionInfo info){
        JSONObject j = new JSONObject();
        j.put("responseType", "sessionInfo");
        j.put("reponse", info.Usrdata.getResponse());
        j.put("email", info.Usrdata.getEmail());
        j.put("password", info.Usrdata.getPassword());
        j.put("address", info.Usrdata.getAddress());
        j.put("phonenumber", info.Usrdata.getPhoneNumber());
        
        for(Item item : info.marketData.getItems()){
            JSONObject i = new JSONObject();
            i.put("ItemID", item.getId());
            i.put("ItemName", item.getName());
            i.put("ItemQuantity", item.getStock());
            i.put("ItemPrice", item.getPrice());
            j.putAll(i);
        }      
        return j;
    }
    
    public static JSONObject jsonifyMarket(Market market){
        JSONObject j = new JSONObject();
        j.put("responseType", "market");
        for(Item item : market.getItems()){
            JSONObject i = new JSONObject();
            i.put("ItemID", item.getId());
            i.put("ItemName", item.getName());
            i.put("ItemQuantity", item.getStock());
            i.put("ItemPrice", item.getPrice());
            j.putAll(i);
        }      
        return j;
    }
    
    public static Login parseLogin(JSONObject login){
        return new Login((String) login.get("email"), (String) login.get("password"));
    }
    
    public static Cart parseCart(JSONObject cartJSON){
        Item[] items = {new Item(), new Item(), new Item(), new Item(), new Item(), new Item(), new Item(), new Item(), new Item(), new Item()};
        for(int i = 1; i <= 10; i++){
            Item item = new Item();
            item.setId(i);
            item.setStock((int) cartJSON.get(i));
            items[i] = item;
        }
        return new Cart(items);
    }
    public static Deposit parseDeposit(JSONObject depositJSON){
        return new Deposit((String) depositJSON.get("email"), (int) depositJSON.get("amount"));
    }
}