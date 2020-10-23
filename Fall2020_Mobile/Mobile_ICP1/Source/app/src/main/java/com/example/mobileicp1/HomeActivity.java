package com.example.mobileicp1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() { // logout button is clicked
            @Override
            public void onClick(View v) { // implements OnClickListener to go from home to login on click
                Intent redirect = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(redirect);
            }
        });
    }
}