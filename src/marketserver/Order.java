/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketserver;

/**
 *
 * @author lenovo
 */
public class Order {
    String dateOrdered;
    double totalCost;

    public Order(String dateOrdered, double totalCost) {
        this.dateOrdered = dateOrdered;
        this.totalCost = totalCost;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    
}
