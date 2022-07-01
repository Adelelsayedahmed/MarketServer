/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;

/**
 *
 * @author tolan
 */
public class Deposit {
    double amount;
    String email;

    public Deposit(String email, double amount) {
        this.amount = amount;
        this.email = email;
    }
}
