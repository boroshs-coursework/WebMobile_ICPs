package com.example.mobileicp1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import android.widget.EditText;
import android.widget.Button;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // used code from ICP instructions and followed tutorials from
        // https://medium.com/@ran_bajra/onclicklistener-vs-onclick-86de30a85ded
        // https://www.tutorialspoint.com/android/android_alert_dialoges.htm
        Button loginButton = findViewById(R.id.loginbuttonView);

        loginButton.setOnClickListener(new View.OnClickListener() { // login button is clicked
            @Override
            public void onClick(View v) { // implements OnClickListener
                EditText usernameCtrl = (EditText) findViewById(R.id.userNameText);
                EditText passwordCtrl = (EditText) findViewById(R.id.passwordText);
                String username = usernameCtrl.getText().toString();
                String password = passwordCtrl.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                if (!username.isEmpty() && !password.isEmpty()){ // verify if username and password have inputs
                    if(username.equals("Admin") && password.equals("Admin")){// if the input is valid (& prev: the button is clicked)
                        // redirect from login main page to home page
                        Intent redirect = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(redirect);
                    }
                    else {// incorrect combo
                        AlertDialog alertInvalid = builder.create();
                        alertInvalid.setCancelable(true);
                        alertInvalid.setMessage("The username and password combination is incorrect.");
                        alertInvalid.show();
                    }
                }
                else {// missing value
                    AlertDialog alertEmpty = builder.create();
                    alertEmpty.setCancelable(true);
                    alertEmpty.setMessage("You need to enter a value for the username and password.");
                    alertEmpty.show();
                }
            }
        });
    }
}