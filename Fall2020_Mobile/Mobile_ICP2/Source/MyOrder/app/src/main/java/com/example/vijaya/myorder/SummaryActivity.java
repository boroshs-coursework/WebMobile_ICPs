package com.example.vijaya.myorder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

public class SummaryActivity extends AppCompatActivity {
    /*
     * Create a text view in the summary activity that holds the summary String from main activity.
     * Use intent to get the data that was passed via intent when changing activity from
     * main-> summary in submitOrder.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // the following two lines just similar to main activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // display the summary data passed via intent in a text view
        Intent summDataIntent = getIntent();
        String summData = summDataIntent.getStringExtra("ORDER_SUMMARY");

        TextView summDataView = (TextView) findViewById(R.id.orderSummary);
        summDataView.setText(summData);
    }

    /*
     * Redirect to order page when 'go to order button' is clicked
     * */
    public void goToOrder(View view){
        Intent gotoMain = new Intent(SummaryActivity.this, MainActivity.class);
        startActivity(gotoMain);
    }
}