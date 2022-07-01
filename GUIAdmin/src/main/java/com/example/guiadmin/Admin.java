package com.example.guiadmin;

public class Admin {

    private String First_Name;
    private String Last_Name;
    private String Email;
    private String Password;
    private String Phone;
    private String Address;
    public boolean Login(String Email, String Password) {
          /*open socket then send to server Email and password
          then take the output of the socketLogin into CorrectLoginInfo
          then pasrse the CorrectLogin info
          if Validation == fasle
          return false
          else
          put all the information
       */

       /* JSONObject LoginRequest = new JSONObject();
        loginRequest.put("requestType", "login");
        loginRequest.put("email", Email) ;
        loginRequest.put("password",Password) ;

        JSONObject LoginRespond;
        String Validation = LoginRespond.get("response")+"";
        if (Validation == "success") {
            String correctemail=LoginRespond.get("email")+"";
            String correctpassword =LoginRespond.get("passeord")+"";
            String correctaddress=LoginRespond.get("address")+"";
            String correctfn = LoginRespond.get("firstname")+"" ;
            String correctln= LoginRespond.get("lastname")+"" ;
            String correctphone = LoginRespond.get("phone")+"" ;
            String tempbalance = LoginRespond.get("balance")+"" ;
            double correctbalance = Double.parseDouble(tempbalance) ;

            this.Email = correctemail ;
            this.Password = correctpassword ;
            this.Address = correctaddress ;
            this.First_Name = correctfn ;
            this.Last_Name = correctln ;
            this.Phone = correctphone ;
            this.balance = correctbalance ;
            return  true ;

        }



        // call the database to get the above information


        else return false ;*/
        if (Email.equals("Adel")&& Password.equals("123"))
            return true ;
        else return false ;
    }

    public boolean Create_Account(String First_name, String Last_Name, String Email, String Password, String Phone, String Address) {

       /*
       JSONObject RegisterRequest = new JSONObject();
        RegisterRequest.put("requestType", "register");
        RegisterRequest.put("firstname", First_name) ;
        RegisterRequest.put("lastname",Last_Name) ;
        RegisterRequest.put("email",Email) ;
        RegisterRequest.put("password",Password) ;
        RegisterRequest.put("phoneno",Phone);
        RegisterRequest.put("address",Address) ;
        */

       /* JSONObject RegisterRespond;

        String Validation = RegisterRespond.get("response")+"";
        if (Validation == "success" ) {
            String correctemail = RegisterRespond.get("email") + "";
            String correctpassword = RegisterRespond.get("passeord") + "";
            String correctaddress = RegisterRespond.get("address") + "";
            String correctfn = RegisterRespond.get("firstname") + "";
            String correctln = RegisterRespond.get("lastname") + "";
            String correctphone = RegisterRespond.get("phone") + "";

            this.First_Name = First_name ;
            this.Last_Name = Last_Name ;
            this.Email = Email ;
            this.Password = Password ;
            this.Phone = Phone ;
            this.Address = Address ;
            return false ; // make the return of the function true or false depends on data base
        }
        */
        this.First_Name = First_name ;
        this.Last_Name = Last_Name ;
        this.Email = Email ;
        this.Password = Password ;
        this.Phone = Phone ;
        this.Address = Address ;
        return true ; // make the return of the function true or false depends on data base


    }
}
