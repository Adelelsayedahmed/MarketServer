/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

/**
 *
 * @author tolan
 */
public class Wallet {
    public static int balance;

    public static int getBalance() {
        return balance;
    }
    
    public static void deposit(int amount){
        balance += amount;
    }
}
