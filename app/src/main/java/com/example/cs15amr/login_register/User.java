package com.example.cs15amr.login_register;

/**
 * Created by cs15aaa2 on 17/11/2016.
 */
public class User {
    String name, username, password;


    public User(String name, String username) {
        this.name = name;

        this.username = username;
        this.password = password;

    }

    public User(String name, String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "";

    }
}
