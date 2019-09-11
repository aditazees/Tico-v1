package com.aditazees.ticov1;
import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class User {

    public String name;
    public String email;
    public String currencytype;
    //public String username;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, String currencytype) {
        this.name = name;
        this.email = email;
        this.currencytype = currencytype;
    }

}