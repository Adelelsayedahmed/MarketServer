package com.example.guiadmin;

public class client {

    private String First_Name;
    private String Last_Name;
    private String Email;
    private String Phone;

    double balance;

        client(String Fn , String Ln , String Email , String Phone , Double balance ){
            this.Email =Email ;
            this.balance = balance ;
            this.First_Name=Fn ;
            this.Last_Name =Ln ;
            this.Phone = Phone ;

        }
    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }





    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
