/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/market", "root", "Omar1801246");
           
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
                rs = st.executeQuery("select Email,Pass from clients where Email = '"+email+"' and Pass = '"+password+"'");
                
                if(rs.next()){
                   
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
                    st.close();
                   con.close();
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
        createConnection();
        try {           
            PreparedStatement st = con.prepareStatement("insert into clients values(?,?,?,?,?,?,?)");
            st.setString(1,register.getEmail());
            st.setString(2,register.getPassword());
            st.setString(3,register.getFirstName());
            st.setString(4,register.getLastName());
            st.setString(5,register.getPhoneNo());
            st.setDouble(6,0);
            st.setString(7,register.getAddress());
            st.execute();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "success";
    }
    
    public static void editStock(Market market){
    Item[] items = market.getItems();
        
        for(int i=0;i<8;i++){
         createConnection();
        try {
           PreparedStatement st = con.prepareStatement("UPDATE item SET quantity = ?  where itemID = ?;");
            st.setInt(1,items[i].getStock());
            st.setInt(2,items[i].getId());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public static void editPrices(Edit edit){
    Item[] items = edit.getItems();
        
        for(int i=0;i<8;i++){
         createConnection();
        try {
           PreparedStatement st = con.prepareStatement("UPDATE item SET price = ? where itemID = ?;");
            st.setDouble(1, items[i].getPrice());
            st.setInt(2,items[i].getId());
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    public static void addOrder(String email,Item[] items,double totalCost){
        
        try {
            // TODO add your handling code here:
            LocalDateTime date = LocalDateTime.now();
            String str = date.toString();
            String strDate= "";
            for(int j=0;j<str.length();j++){
           if(j!=10 && j<19){
              strDate += str.charAt(j);
           }
           if(j == 10){
               strDate +=" ";
           }
            } 
          
            date =  date.plusHours(2);
             String s = date.toString();
             String sDate = "";
             for(int j=0;j<s.length();j++){
           if(j!=10 && j<19){
              sDate += s.charAt(j);
           }
           if(j == 10){
               sDate +=" ";
           }
            } 
           for(int i=0;i<8;i++){
               if(items[i].getStock() != 0){
            createConnection();
            PreparedStatement st = con.prepareStatement("insert into buy values(?,?,?,?,?)");
            st.setString(1, email);
            st.setDouble(2, items[i].getId());
            st.setString(3, strDate);
            st.setString(4, sDate);
            st.setDouble(5, totalCost);
            st.execute();
            st.close();
            con.close();
               }
           }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        
         }
    }
    
    public static ArrayList<UsrData> getUsers(){
        createConnection();
        ArrayList<UsrData> users = new ArrayList<UsrData>();
        try {
           
                Statement st = con.createStatement();
                ResultSet rs=st.executeQuery("select * from clients");
                String email = null;
                String password = null;
                String Fname = null;
                String Lname = null;
                String phNo = null;
                String address = null;
                Double balance = null;
                while(rs.next()){
                    email = rs.getString("Email");
                    password = rs.getString("Pass");
                    Fname=rs.getString("Fname");
                    Lname=rs.getString("Lname");
                    phNo=rs.getString("Phone");
                    balance = rs.getDouble("Balance");
                    address = rs.getString("Address");
                    users.add(new UsrData(email,password,Fname,Lname,phNo,address,balance));
                }
               
            }catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        return users;
    }
    
    public static void deleteUser(String email){
        createConnection();
             try {
           
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("delete from clients where Email = '"+email+"'"); 
                st.close();
                con.close();
         }catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static ArrayList<Order> getOrders(String email){
        createConnection();
        ArrayList<Order> orders = new ArrayList<Order>();
             try {
           
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select distinct datesub,totalCost from buy where Email = '"+email+"'"); 
            String date = null;
            double total = 0;
            while(rs.next()){
               date =rs.getString("datesub");
               total = rs.getDouble("totalCost");
               orders.add(new Order(date,total));
            }
                st.close();
                con.close();
         }catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
             return orders;
    }
}
