/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guiclient;




public class Item {
    protected String ItemName ;
    protected int ItemID ;
    protected double price ;
    protected int stock ;
    public  int bq ;


    Item(String ItemName  , double price , int stock , int itemID ,int bq) {
        this.ItemName = ItemName ;
        this.price    = price    ;
        this.stock    = stock    ;
        this.ItemID   = itemID   ;
        this.bq       = bq       ;
    }
    Item(){

    }
    public String getItemName() {
        return ItemName;
    }

    public int getItemID() {
        return ItemID;
    }
    public int getItemStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public int getBq() {
        return bq;
    }



}
