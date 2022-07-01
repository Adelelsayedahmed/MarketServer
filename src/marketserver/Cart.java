/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marketserver;
/**
 *
 * @author tolan
 */
public class Cart {
    Item[] items;
    String email;
    double totalCost;
    
    public Cart(Item[] items, String email){
        this.items = items;
        this.email = email;
        this.totalCost = calculateTotal();
    }
    
    public double calculateTotal() {
        double cost = 0;
        for(Item i : items){
            cost += i.getStock() * i.getPrice();
        }
        return cost;
    }  
}
