package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public String number;
    public String primaryBus;
    public String address;
    public String province;
    public  String email;


    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, String name, String number, String primaryBus, String address,
                   String province, String email){
        this.uid = uid;
        this.name = name;
        this.number = number;
        this.primaryBus = primaryBus;
        this.address = address;
        this.province = province;
        this.email = email;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("number", number);
        result.put("primaryBus", primaryBus);
        result.put("address", address);
        result.put("province", province);
        result.put("email", email);

        return result;
    }
}
