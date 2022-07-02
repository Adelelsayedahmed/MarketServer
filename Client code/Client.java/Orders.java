package com.example.guiclient;

public class Orders {
    private String Date ;
    private double Price ;

    Orders(String Date , double Price){
        this.Date = Date ;
        this.Price = Price ;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
