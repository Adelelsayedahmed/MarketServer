/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.util.ArrayList;
import org.json.simple.JSONObject;
/**
 *
 * @author tolan
 */
public class JSON {
    public static JSONObject jsonifySessionInfo(SessionInfo info){
        JSONObject j = new JSONObject();
        j.put("responseType", "sessionInfo");
        j.put("response", info.Usrdata.getResponse());
        j.put("email", info.Usrdata.getEmail());
        j.put("password", info.Usrdata.getPassword());
        j.put("address", info.Usrdata.getAddress());
        j.put("phonenumber", info.Usrdata.getPhoneNumber());
        j.put("firstname", info.Usrdata.getFname());
        j.put("lastname", info.Usrdata.getLname());
        j.put("balance", info.Usrdata.getBalance());
        
        for(int i = 1; i <= 8; i++){
            JSONObject item = new JSONObject();
            item.put("ItemID"+i, info.marketData.getItems()[i-1].getId());
            item.put("ItemName"+i, info.marketData.getItems()[i-1].getName());
            item.put("ItemQuantity"+i, info.marketData.getItems()[i-1].getStock());
            item.put("ItemPrice"+i, info.marketData.getItems()[i-1].getPrice());
            j.putAll(item);
        }      
        return j;
    }
    public static JSONObject jsonifyUsers(ArrayList<UsrData> users){
        JSONObject usersJSON = new JSONObject();
        for(int i = 1; i <= users.size(); i++){
            JSONObject user = new JSONObject();
            user.put("email"+i, users.get(i-1).getEmail());
            user.put("firstName"+i, users.get(i-1).getFname());
            user.put("lastName"+i, users.get(i-1).getLname());
            user.put("address"+i, users.get(i-1).getAddress());
            user.put("phoneno"+i, users.get(i-1).getPhoneNumber());
            user.put("balance"+i, users.get(i-1).getBalance());
            usersJSON.putAll(user);
        }
        return usersJSON;
    }
    
    public static JSONObject jsonifyMarket(Market market){
        JSONObject j = new JSONObject();
        j.put("responseType", "market");
        for(int i = 1; i <= 8; i++){
            JSONObject item = new JSONObject();
            item.put("ItemID"+i, market.getItems()[i-1].getId());
            item.put("ItemName"+i, market.getItems()[i-1].getName());
            item.put("ItemQuantity"+i, market.getItems()[i-1].getStock());
            item.put("ItemPrice"+i, market.getItems()[i-1].getPrice());
            j.putAll(item);
        }      
        return j;
    }
    
    public static Login parseLogin(JSONObject login){
        return new Login((String) login.get("email"), (String) login.get("password"));
    }
    
    public static Cart parseCart(JSONObject cartJSON){
        System.out.println("parsing: " + cartJSON.toString());
        Item[] items = new Item[8];
        for(int i = 1; i <= 8; i++){
            Item item = new Item();
            item.setId(i);
            item.setStock((int) cartJSON.get(i+""));
            items[i-1] = item;
        }
        System.out.println("Done");
        return new Cart(items, (String) cartJSON.get("email"));
    }
    public static Deposit parseDeposit(JSONObject depositJSON){
        return new Deposit((String) depositJSON.get("email"), (double) depositJSON.get("amount"));
    }
    
    public static Register parseRegister(JSONObject registerJSON){
        Register register = new Register();
        register.setFirstName((String) registerJSON.get("firstname"));
        register.setLastName((String) registerJSON.get("lastname"));
        register.setAddress((String) registerJSON.get("address"));
        register.setPhoneNo((String) registerJSON.get("phoneno"));
        register.setPassword((String) registerJSON.get("password"));
        register.setEmail((String) registerJSON.get("email")); 
        register.setResponse (DBManager.addUser(register));
        return register;
    }
    
    public static Edit parseEditPrices(JSONObject editPricesJSON){
        Item[] items = new Item[8];
        for(int i = 1; i <= 8; i++){
            Item item = new Item();
            item.setPrice((int) editPricesJSON.get(i));
            items[i-1] = item;
        }
        return new Edit(items);
    }
    
    public static Edit parseEditStock(JSONObject editStockJSON){
        Item[] items = {new Item(), new Item(), new Item(), new Item(), new Item(), new Item(), new Item(), new Item()} ;
        for(int i = 1; i <= 8; i++){
            Item item = new Item();
            item.setStock((int) editStockJSON.get(i));
            items[i-1] = item;
        }
        return new Edit(items);
    }
    
}