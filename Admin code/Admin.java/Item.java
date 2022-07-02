package com.example.guiadmin;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;

public class Item {
    private String ItemName ;
    private double price ;
    private int stock ;



    Item(String ItemName  , double price , int stock  ) {
        this.ItemName = ItemName ;
        this.price    = price    ;
        this.stock    = stock    ;

    }

    public String getItemName() {
        return ItemName;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}


