package com.example.cs15amr.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {


    Button bRegister;
    EditText etName, etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRegister:
                startActivity(new Intent(this, Login.class));

               String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();



                User user = new User(name,username, password);

                registerUser (user);
                break;
        }
    }

    private void registerUser(User user){
//        ServerRequests serverRequests = new ServerRequests(this);
//        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
//            @Override
//            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
//        });
//    }
}