/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marketserver;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author tolan
 */
public class MarketServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerListener.start(6969);
    }
    
}