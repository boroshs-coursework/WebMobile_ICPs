package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ActivityNotFoundException;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final float PIZZA_PRICE = 3.00f;
    final float PEPPERONI_PRICE = 2.00f;
    final float SAUSAGE_PRICE = 1.50f;
    final float MUSHROOM_PRICE = 0.50f;
    final float OLIVE_PRICE = 0.50f;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * submitOrder is called when the Summary button is clicked.
     * Write the explicit intent here to go from main page to summary once summary button is clicked
     * https://stackoverflow.com/questions/3591465/on-android-how-do-you-switch-activities-programmatically
     * Also send data of the summary along with the intent
     * https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
     */
    public void submitOrder(View view) {
        Intent gotoSummary = new Intent(MainActivity.this, SummaryActivity.class);
        String summary = getOrderSummaryMessage();
        gotoSummary.putExtra("ORDER_SUMMARY", summary);
        startActivity(gotoSummary);
    }

    /**
     * The method sendEmail is called when the Order button is clicked
     * written using implicit intent.
     * https://medium.com/@cketti/android-sending-email-using-intents-3da63662c58f
     */
    public void sendEmail(View view) {
        // name
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
        // email
        EditText userEmailView = (EditText) findViewById(R.id.user_input2);
        String userEmail = userEmailView.getText().toString();
        String address = "mailto:"+userEmail;
        // subject
        String subject = getString(R.string.email_subject, userInputName);
        // email body
        String body = getOrderSummaryMessage();
        body = body.replaceAll(Pattern.quote("\n"), "<br>");

        String mailto = address +
                "?cc=" + "sbrcb@umsystem.edu" +
                "&subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(body);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Context context = getApplicationContext();
            String noEmail = getString(R.string.no_email_client);
            Log.i("MainActivity", noEmail);

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, noEmail, duration);
            toast.show();
        }
    }

    /**
     * Method to turn boolean into yes/no
     */
    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasPepperoni,
                                 boolean hasSausage,
                                 boolean hasMushroom,
                                 boolean hasOlive) {
        float basePrice = PIZZA_PRICE;
        if (hasPepperoni) {
            basePrice += PEPPERONI_PRICE;
        }
        if (hasSausage) {
            basePrice += SAUSAGE_PRICE;
        }
        if (hasMushroom) {
            basePrice += MUSHROOM_PRICE;
        }
        if (hasOlive) {
            basePrice += OLIVE_PRICE;
        }

        return quantity * basePrice;
    }

    /**
     * Method to get the order summary message string returned
     * **/
    private String getOrderSummaryMessage(){
        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if pepperoni is selected
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();
        // check if sausage is selected
        CheckBox sausage = (CheckBox) findViewById(R.id.sausage_checked);
        boolean hasSausage = sausage.isChecked();
        // check if mushroom is selected
        CheckBox mushroom = (CheckBox) findViewById(R.id.mushroom_checked);
        boolean hasMushroom = mushroom.isChecked();
        // check if olive is selected
        CheckBox olive = (CheckBox) findViewById(R.id.olive_checked);
        boolean hasOlive = olive.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasPepperoni, hasSausage, hasMushroom, hasOlive);

        // create and store the order summary
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n\n" +
                getString(R.string.order_summary_pepperoni, boolToString(hasPepperoni)) + "\n" +
                getString(R.string.order_summary_sausage, boolToString(hasSausage)) + "\n" +
                getString(R.string.order_summary_mushroom, boolToString(hasMushroom)) + "\n" +
                getString(R.string.order_summary_olive, boolToString(hasOlive)) + "\n\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, totalPrice) + "\n\n" +
                getString(R.string.thank_you, userInputName);

        return orderSummaryMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of pizza by one
     *
     * @param view on passes the view that we are working with to the method
     */
    public void increment(View view) {
        if (quantity < 30) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_much_pizza);
            Log.i("MainActivity", upperLimitToast);

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
        }
    }

    /**
     * This method decrements the quantity of pizzas by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_little_pizza);
            Log.i("MainActivity", lowerLimitToast);

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
        }
    }
}