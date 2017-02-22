package com.example.cs15amr.login_register;

import android.content.SharedPreferences;
import android.content.Context;
/**
 * Created by cs15aaa2 on 17/11/2016.
 */
public class UserLocalStore { //This class allows us to store data
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase; //allows you to store data


    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);


    }

    //Generate methods to get data from the local database

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();

    }

    public User getLoggedInUser() { //Get user that is currently logged on
        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User storedUser = new User(name, username, password);

        return storedUser;

    }

    public void setUserLoggedIn (boolean loggedIn){ //if the user is logged in set to true otherwise false
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("Logged in", loggedIn);
    }


    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("Logged in", false) == true) {
            return true;
        }else{
            return false;

        }
    }

    public void clearUserData() { //clear user data when user is logging out
         SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
