package com.vsga_xenchinryu.healthyapp.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class DataLogin {
    @PrimaryKey(autoGenerate = true)
    long id;
    String name;
    String email;
    String numberPhone;
    String password;

    public DataLogin(long id, String name, String email, String numberPhone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.numberPhone = numberPhone;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getPassword() {
        return password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
