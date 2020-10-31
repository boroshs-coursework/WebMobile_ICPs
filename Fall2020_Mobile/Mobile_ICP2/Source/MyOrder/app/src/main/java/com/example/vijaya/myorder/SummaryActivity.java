package com.example.vijaya.myorder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

public class SummaryActivity extends AppCompatActivity {
    // create a text view in the activity that holds the summary from main java
    // base code is copied from main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //display the summary data in a text view
        Intent summDataIntent = getIntent();
        String summData = summDataIntent.getStringExtra("ORDER_SUMMARY");
        TextView summDataView = (TextView) findViewById(R.id.orderSummary);
        summDataView.setText(summData);
    }

    // redirect to order page when go to order button is clicked
    // base code on submitOrder
    public void goToOrder(View view){
        Intent gotoMain = new Intent(SummaryActivity.this, MainActivity.class);
        startActivity(gotoMain);
    }
}