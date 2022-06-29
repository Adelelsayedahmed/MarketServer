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
    
    public static SessionInfo loginRequest(String email, String password){
        //Send request to database
        UsrData info = DBManager.getUser(email, password); // Recieved from database
        
        if(info.getResponse() == "success"){
            SessionInfo session = SessionDispatcher.getSessionInfo(info);
            return session;
        } else {
            SessionInfo session = new SessionInfo();
            session.Usrdata.setResponse("failed");
            return session;
        }
    }
    
}
