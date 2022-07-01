/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guiclient;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.json.simple.JSONObject;
public  class Client {
    Client() {
        CartObeject = new Cart();
    }

    Cart CartObeject;
    private String First_Name;
    private String Last_Name;
    private String Email;
    private String Password;
    private String Phone;
    private String Address;
    double balance;

    public Cart getCartObeject() {
        return CartObeject;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public double getBalance() {
        return balance;
    }

    public boolean Create_Account(String First_name, String Last_Name, String Email, String Password, String Phone, String Address) {


        try{
            Socket connection = new Socket(StartController.IP, 6969);

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject RegisterRequest = new JSONObject();
                RegisterRequest.put("requestType", "register");
                RegisterRequest.put("firstname", First_name) ;
                RegisterRequest.put("lastname",Last_Name) ;
                RegisterRequest.put("email",Email) ;
                RegisterRequest.put("password",Password) ;
                RegisterRequest.put("phoneno",Phone);
                RegisterRequest.put("address",Address) ;

                out.writeObject(RegisterRequest);


                JSONObject RegisterRespond = (JSONObject) in.readObject();
               System.out.println( RegisterRespond.toString());
                String Validation = RegisterRespond.get("response")+"";
                if (Validation .equals( "success") ) {
                    this.First_Name = First_name ;
                    this.Last_Name = Last_Name ;
                    this.Email = Email ;
                    this.Password = Password ;
                    this.Phone = Phone ;
                    this.Address = Address ;
                    this.balance = 0 ;
                    out.close();
                    in.close();
                    connection.close();
                    return true ;
                }

                else {
                    out.close();
                    in.close();
                    connection.close();
                    return false ;
                }
            }



        }catch(Exception e){return false;}

    }

    public boolean Login(String Email, String Password) {

        try{
            Socket connection = new Socket(StartController.IP, 6969);

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject LoginRequest = new JSONObject();
                LoginRequest.put("requestType", "login");
                LoginRequest.put("email", Email) ;
                LoginRequest.put("password",Password) ;
                out.writeObject(LoginRequest);

                    JSONObject LoginRespond = (JSONObject) in.readObject();
                    String  Validation = LoginRespond.get("response")+"";
                    System.out.print(LoginRespond.toString());
                if (Validation.equals ("success")) {
                    String correctemail=LoginRespond.get("email")+"";
                    String correctpassword =LoginRespond.get("password")+"";
                    String correctaddress=LoginRespond.get("address")+"";
                    String correctfn = LoginRespond.get("firstname")+"" ;
                    String correctln= LoginRespond.get("lastname")+"" ;
                    String correctphone = LoginRespond.get("phonenumber")+"" ;
                    String tempbalance = LoginRespond.get("balance")+"" ;
                    double correctbalance = Double.parseDouble(tempbalance) ;

                    this.Email = correctemail ;
                    this.Password = correctpassword ;
                    this.Address = correctaddress ;
                    this.First_Name = correctfn ;
                    this.Last_Name = correctln ;
                    this.Phone = correctphone ;
                    this.balance = correctbalance ;
                    out.close();
                    in.close();
                    connection.close();
                    return  true ;

                }


                else{
                    out.close();
                    in.close();
                    connection.close();
                    return false ;
                }



            }

        }
        catch(Exception e){return false ;}


    }

    public void AddToCart(Item item , int quantity) {CartObeject.addItem(item, quantity);}


    public void Delete_item (Item item){
        CartObeject.DeleteItem(item);
    }
    public void deposit (double money ){
        this.balance= this.balance+money ;

        try{
            Socket connection = new Socket(StartController.IP, 6969);

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject depositRequest = new JSONObject();
                depositRequest.put("requestType", "deposit");
                depositRequest.put("email", this.Email);
                depositRequest.put("amount", money);
                out.writeObject(depositRequest);
                break;
            }

            out.close();
            in.close();
            connection.close();

        }catch(Exception e){}

    }

    public String purchase(){


        double total_amount = CartObeject.CalculateTotalCartPrice() ;
        if (total_amount <=this.balance){

            try{
                Socket connection = new Socket(StartController.IP, 6969);

                ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

                while(true){
                    JSONObject CheckCart = new JSONObject();
                    CheckCart.put("requestType", "cart");
                    CheckCart.put("email", this.Email);

                    for (int i = 1 ; i<=8 ; i++) {
                        CheckCart.put(i+"", itemshoppingcontroller.MarketItemObject.get(i-1).bq);
                    }

                    out.writeObject(CheckCart);
                    JSONObject response = (JSONObject) in.readObject();
                    System.out.println(response.toString()) ;
                   if((response.get(("response")+"").equals("success")) ){
                       out.close();
                       in.close();
                       connection.close();
                       itemshoppingcontroller.ItemsName.clear();
                       itemshoppingcontroller.ItemsPrice.clear();
                       itemshoppingcontroller.ItemsStock.clear();
                       itemshoppingcontroller.MarketItemObject.clear();

                       for (int i = 1 ; i<=8 ; i++){
                           itemshoppingcontroller.ItemsName.add(response.get("ItemName"+i)+"");
                       }
                       for (int i = 1 ; i<=8 ; i++){
                           itemshoppingcontroller.ItemsPrice.add(Double.valueOf(response.get("ItemPrice"+i)+""));
                       }
                       for (int i = 1 ; i<=8 ; i++){
                           itemshoppingcontroller.ItemsStock.add((int)(response.get("ItemQuantity"+i)));
                       }
                       itemshoppingcontroller.MarketItemObject() ;



                   return "true" ;
                   }
                   else { out.close();
                         in.close();
                         connection.close();

                       itemshoppingcontroller.ItemsName.clear();
                       itemshoppingcontroller.ItemsPrice.clear();
                       itemshoppingcontroller.ItemsStock.clear();
                       itemshoppingcontroller.MarketItemObject.clear();

                       for (int i = 1 ; i<=8 ; i++){
                           itemshoppingcontroller.ItemsName.add(response.get("ItemName"+i)+"");
                       }
                       for (int i = 1 ; i<=8 ; i++){
                           itemshoppingcontroller.ItemsPrice.add(Double.valueOf(response.get("ItemPrice"+i)+""));
                       }
                       for (int i = 1 ; i<=8 ; i++){
                           itemshoppingcontroller.ItemsStock.add((int)(response.get("ItemQuantity"+i)));
                       }
                       itemshoppingcontroller.MarketItemObject() ;

                       return "not available" ;

                    }
                }



            }catch(Exception e){
                return "not available" ;
            }
        }
        else return "insufficient" ;

      /*  // call database to check if items are still avaliable or not and save the new information of items
        if (items_flag == true){
            return "true" ;
        }
        else if (items_flag == false  )
            return "not available";
*/

    }

    public int search (String Iname){
        for (int i = 0 ; i <itemshoppingcontroller.MarketItemObject.size();i++){
            if (Iname.equals(itemshoppingcontroller.MarketItemObject.get(i).ItemName))
                return i ;
        }
         return -1 ;
    }

    
}
