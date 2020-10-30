package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendEmail(View view){
// https://medium.com/@cketti/android-sending-email-using-intents-3da63662c58f
        String mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode("subject") +
                "&body=" + Uri.encode("bodyText");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }

        // ICP code given
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        if (intent.resolveActivity(getPackageManager()) !=null){
//            startActivity(intent);
//        }
// https://stackoverflow.com/questions/13848423/opening-email-client-via-intent-but-not-to-send-a-message/13849013
//        final Intent emailLauncher = new Intent(Intent.ACTION_VIEW);
//        emailLauncher.addCategory(Intent.CATEGORY_APP_EMAIL);
//        emailLauncher.setType("message/rfc822");
//        try{
//            startActivity(emailLauncher);
//        }catch(ActivityNotFoundException e){
//
//        }

//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_APP_EMAIL);
//        try{
//            startActivity(intent);
//        } catch(ActivityNotFoundException e){
//
//        }
//
    }
}