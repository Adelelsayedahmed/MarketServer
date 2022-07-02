package com.example.guiadmin;

import org.json.simple.JSONObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Admin {


    public boolean Login(String Email, String Password) {
         if (Email.equals("Admin")&&Password.equals("Admin"))
             return true ;
         else return false ;
    }

    public boolean Create_Account (String Fn , String Ln , String Email , String Password,String PhoneNumber , String Address){

        try{
            Socket connection = new Socket(startcontroller.IP, 6969);

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());

            while(true){
                JSONObject RegisterRequest = new JSONObject();
                RegisterRequest.put("requestType", "register");
                RegisterRequest.put("firstname", Fn) ;
                RegisterRequest.put("lastname",Ln) ;
                RegisterRequest.put("email",Email) ;
                RegisterRequest.put("password",Password) ;
                RegisterRequest.put("phoneno",PhoneNumber);
                RegisterRequest.put("address",Address) ;

                out.writeObject(RegisterRequest);


                JSONObject RegisterRespond = (JSONObject) in.readObject();
                System.out.println( RegisterRespond.toString());
                String Validation = RegisterRespond.get("response")+"";
                if (Validation .equals( "success") ) {
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


}
