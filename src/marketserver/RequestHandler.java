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
    
    public static String loginRequest(String username, String password, String ip){
        //Send request to database
        UsrData info = new UsrData(); // Recieved from database
        
        if(info.getResponse() == "success"){
            SessionDispatcher.sendSessionInfo(ip, info);
            return info.getResponse();
        } else {
            // Send login status to Client
            return info.getResponse();
        }
    }
    
}
