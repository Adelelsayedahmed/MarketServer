/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tolan
 */
public class DBManager {
    
     static Connection con;
  
    
    
    private static void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/market", "root", "Aa01030882089");
           
            //System.out.println("Successully Connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UsrData getUser(String email, String password){
        UsrData user = new UsrData();
        createConnection();
         try {
           
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select Email from clients where Email = '"+email+"'");
            if(rs.next()){
                rs = st.executeQuery("select Email,password from clients where Email = '"+email+"' and password = '"+password+"'");
                
                if(rs.next()){
                    st.close();
                   con.close();
                   rs =st.executeQuery("select * from clients where Email = '"+email+"'");
                 
                String Fname = null;
                String Lname = null;
                String phNo = null;
                String address = null;
                Double balance = null;
                
                   while(rs.next()){
                    Fname=rs.getString("Fname");
                    Lname=rs.getString("Lname");
                    phNo=rs.getString("Phone");
                    balance = rs.getDouble("Balance");
                    address = rs.getString("Address");
                }
                   user.setEmail(email);
                   user.setPassword(password);
                   user.setFname(Fname);
                   user.setLname(Lname);
                   user.setPhoneNumber(phNo);
                   user.setAddress(address);
                   user.setBalance(balance);
                   user.setResponse("success");
                }
                else{
                    st.close();
                    con.close();
                    user.setResponse("failed");

                }

            }
            else{
                st.close();
                con.close();
                user.setResponse("failed");
            }   
     
        
        
         }catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       
  
        return user;
    } 
    
    public static Market getMarket(){
        Market market = new Market();
        Item[] items = new Item[8];
         createConnection();
         int i=0;
            try {
           
                Statement st = con.createStatement();
                ResultSet rs=st.executeQuery("select * from item");
                int id = 0;
                String name = null;
                double price = 0;
                int stock = 0;
                while(rs.next()){
                    id=rs.getInt("itemID");
                    name=rs.getString("itemName");
                    price = rs.getDouble("price");
                    stock=rs.getInt("quantity");
                    Item item = new Item();
                    item.setId(id);
                    item.setName(name);
                    item.setPrice(price);
                    item.setStock(stock);
                 if(i<8){
                   items[i] = item;
                 }
                    
                    i++;
                }
                market.setItems(items);
                st.close();
               con.close();
            }catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        return market;
    }
    
    public static void setMarket(Market market){
        // update market values in db
        Item[] items = market.getItems();
        
        for(int i=0;i<8;i++){
         createConnection();
        try {
           PreparedStatement st = con.prepareStatement("UPDATE item SET itemID = ? ,itemName = ? , price = ? , quantity = ? ;");
           
            st.setInt(1,items[i].getId());
            st.setString(2,items[i].getName());
            st.setDouble(3, items[i].getPrice());
            st.setInt(4,items[i].getStock());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public static void deposit(String email, double Amount){
        createConnection();
        try {
            PreparedStatement st = con.prepareStatement("UPDATE clients SET  Balance = Balance + ?"
                    + " WHERE email = ?;");
           
            st.setDouble(1,Amount);
            st.setString(2,email);
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String addUser(Register register){
        return "success";
    }
    
    public static void editStock(Edit edit){
    
    }
    
    public static void editPrices(Edit edit){
    
    }
   
}
