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
    int totalCost;
    
    public Cart(Item[] items){
        this.items = items;
        this.totalCost = calculateTotal();
    }
    
    public int calculateTotal() {
        int cost = 0;
        for(Item i : items){
            cost += i.getStock() + i.getPrice();
        }
        return cost;
    }  
}
