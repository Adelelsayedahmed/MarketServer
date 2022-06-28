/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

/**
 *
 * @author tolan
 */
import java.util.ArrayList;
public class SessionDispatcher {
    static ArrayList<String> activeSessions = new ArrayList<String>();
    
    
    public static void sendSessionInfo(String ip, UsrData userInfo){
        activeSessions.add(ip);
        SessionInfo sessionData = new SessionInfo();
        sessionData.Usrdata = userInfo;
        sessionData.marketData = MarketManager.getMarket();
        // add walletInfo and marketInfo
        // send session info to client throught sockets
    }
    
    public static void sendSessionUpdate(Market market){ // also sent wallet for wallet updates
        for(String ip : activeSessions){
            // send session update to client through sockets
        }
    }
    
    
    
    
}
