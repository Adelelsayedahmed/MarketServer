/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

/**
 *
 * @author tolan
 */
public class SessionDispatcher {
    
    public static SessionInfo getSessionInfo(String email, String password){
        SessionInfo sessionData = new SessionInfo();
        sessionData.Usrdata = DBManager.getUser(email, password);
        MarketManager.getMarketData();
        sessionData.marketData = MarketManager.getMarketData();
        return sessionData;
    }
}
