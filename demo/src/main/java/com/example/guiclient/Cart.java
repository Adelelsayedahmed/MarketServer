/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.guiclient;

import javafx.event.Event;

import java.util.*;


public class Cart {

    Cart(){
        CartItems = new ArrayList<>();
        quantity = new ArrayList<>();

    }
    public ArrayList<Item> CartItems ;
    public ArrayList<Integer> quantity ;

    public void addItem(Item item, int q) {
        String ItemName = item.getItemName();
      for (int i = 0; i < CartItems.size(); i++) {
            if (ItemName.equals(CartItems.get(i).getItemName())) {
                // this item was added before
                CartItems.set(i, coloning(item,q));
                quantity.set(i, quantity.get(i) + q);
                return;
            }
        }

        CartItems.add(coloning(item,q));
        quantity.add(q);

    }
    public Item coloning(Item item , int q ){
      item.bq+=q ;
      return item ;
    }

    public void DeleteItem(Item item) {
        String ItemName = item.getItemName();
        for (int i = 0; i < CartItems.size(); i++) {
            if (ItemName.equals(CartItems.get(i).getItemName())) {
                // The item found in the list
                CartItems.remove(i);
                return;
            }
        }
    }
    public void EditQuantity(Item item , int Q){}

    public double CalculateTotalCartPrice() {
        double TotalPrice = 0;
        for (int i = 0; i < CartItems.size(); i++) {
            TotalPrice += (CartItems.get(i).getPrice() * CartItems.get(i).bq);
        }
        return TotalPrice;
    }


    public void emptyCart() {
        for (int  i = 0 ; i<CartItems.size();i++){
            CartItems.get(i).bq = 0 ;
        }
        CartItems.clear();
        quantity.clear();

    }



}
