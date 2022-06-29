/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

/**
 *
 * @author tolan
 */
public class RequestHandler {
    
    public static UsrData loginRequest(String email, String password){
        //Send request to database
        UsrData info = DBManager.getUser(email, password); // Recieved from database
        return info;
    }
    
}
