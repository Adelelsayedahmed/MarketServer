/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
import java.io.*;

/**
 *
 * @author tolan
 */
public class Login implements Serializable {
    private String ipAddress;
    private String userName;
    private String password;
    
    private String response;
    private String loginStatus;

    public Login(String ipAddress, String userName, String password) {
        this.ipAddress = ipAddress;
        this.userName = userName;
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setClientaddress(String clientaddress) {
        this.ipAddress = clientaddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    //public void login(){
      //  this.loginStatus = RequestHandler.loginRequest(this.userName, this.password, this.ipAddress);
        // status is sent to client
    //}
    
    
}
