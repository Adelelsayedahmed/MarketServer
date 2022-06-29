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
    
    public static SessionInfo getSessionInfo(UsrData userInfo){
        SessionInfo sessionData = new SessionInfo();
        sessionData.Usrdata = userInfo;
        sessionData.marketData = MarketManager.getMarket();
        sessionData.response = "success";
        return sessionData;
    }
}
