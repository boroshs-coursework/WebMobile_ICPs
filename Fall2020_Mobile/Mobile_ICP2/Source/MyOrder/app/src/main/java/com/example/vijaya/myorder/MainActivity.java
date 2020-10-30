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

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int COFFEE_PRICE = 5;
    final int WHIPPED_CREAM_PRICE = 1;
    final int CHOCOLATE_PRICE = 2;
    //final int WHIPPED_CREAM_PRICE = 1;
    //final int CHOCOLATE_PRICE = 2;
    int quantity = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * submitOrder is called when the Summary button is clicked.
     */
    public void submitOrder(View view) {
        // Write the explicit intent here to go from main page to summary once summary button is clicked
        // https://stackoverflow.com/questions/3591465/on-android-how-do-you-switch-activities-programmatically
        // Also send data of the summary along with the intent
        // https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
        Intent gotoSummary = new Intent(MainActivity.this, SummaryActivity.class);
        gotoSummary.putExtra("ORDER_SUMMARY", getOrderSummaryMessage(view));
        startActivity(gotoSummary);
    }

    /**
     * The method sendEmail is called when the Order button is clicked
     */
    // written using implicit intent
    // https://www.javatpoint.com/how-to-send-email-in-android-using-intent
    public void sendEmail(String name, String output) {
        //EditText userEmailView = (EditText) findViewById(R.id.user_input2);
        //String userEmail = userEmailView.getText().toString();
        /*String body = getOrderSummaryMessage(view);

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("text/plain"); // otherwise error
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"sbrc@umsystem.edu"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Pizza Order");
        email.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(email);*/

//        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                "mailto","abc@gmail.com", null));
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sbrc@umsystem.edu"}); // String[] addresses
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
//        startActivity(Intent.createChooser(emailIntent, "Send email..."));

        /*if (email.resolveActivity(getPackageManager()) !=null){
            startActivity(email);
        } else {
            Context context = getApplicationContext();
            String msg = "Failed to open email client";
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasWhippedCream, boolean hasChocolate, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_whipped_cream, boolToString(hasWhippedCream)) + "\n" +
                getString(R.string.order_summary_chocolate, boolToString(hasChocolate)) + "\n" +
                //getString(R.string.order_summary_chocolate, boolToString(hasChocolate)) + "\n" +
                //getString(R.string.order_summary_chocolate, boolToString(hasChocolate)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = COFFEE_PRICE;
        if (hasWhippedCream) {
            basePrice += WHIPPED_CREAM_PRICE;
        }
        if (hasChocolate) {
            basePrice += CHOCOLATE_PRICE;
        }
        /*
        if (hasChocolate) {
            basePrice += CHOCOLATE_PRICE;
        }
        if (hasChocolate) {
            basePrice += CHOCOLATE_PRICE;
        }
        */
        return quantity * basePrice;
    }

    /** Method to get the order summary message string **/
    private String getOrderSummaryMessage(View view){
        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();

        // check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();

        /*
        // check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();

        // check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();
        */

        // calculate and store the total price
        float totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasWhippedCream, hasChocolate, totalPrice);

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
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}